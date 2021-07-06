package com.sj.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sj.board.dto.BoardDTO;
import com.sj.board.dto.PageInfoDTO;
import com.sj.board.paging.Pagenation;
import com.sj.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BoardController {

	private final BoardService boardService;
	private final Map<String, Object> user;

	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;

		user = new HashMap<String, Object>();
		user.put("id", "sj");

	}

	@GetMapping
	public String index(HttpServletRequest request, HttpServletResponse response) {


		return "index";
	}


	@GetMapping("/list")
	public ModelAndView list(HttpServletRequest request, @RequestParam(required = false, defaultValue = "1") String currentPage) {
		ModelAndView mav = new ModelAndView();


		//////////////////////////////////////////////////////

		/*기본 페이지 설정*/
		int pageNo = 1;
		int page;

		try {
			page = Integer.parseInt(currentPage);
		} catch(Exception e) {
			page = 1;
		}
		//////////////////////////////////////////////////////
		if(page <= 0) {
			pageNo = 1;
		}else {
			pageNo = page;
		}
		//////////////////////////////////////////////////////
		/*전체 게시물 수 조회*/
		int totalCount = boardService.selectCount();


		/*paging*/
		PageInfoDTO pageInfo = Pagenation.getPageInfo(pageNo, totalCount);

		List<BoardDTO> list = boardService.selectList(pageInfo);

		mav.addObject("list", list);
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("board/list");

		return mav;
	}

	@GetMapping("/write")
	public ModelAndView write() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("board/write");
		return mav;
	}

	@PostMapping("/write")
	public ModelAndView write(@ModelAttribute BoardDTO board) {
		ModelAndView mav = new ModelAndView();
		boardService.insertBoard(board);


		mav.setViewName("redirect:/list");

		return mav;
	}

	@GetMapping("/detail/{number}")
	public ModelAndView detail(@CookieValue(value="check", required = false) String check, @PathVariable long number, HttpServletRequest request, HttpServletResponse response) {

		Cookie[] cookies = request.getCookies();
		Cookie ck = null;
		
		if(cookies != null && cookies.length > 0) {
			
			for(Cookie cookie : cookies) {
				log.info("-=======================");
				log.info("cookie Value : {}", cookie.getValue());
				log.info("cookie name : {}", cookie.getName());
				log.info("cookie path : {}", cookie.getPath());
				log.info("-=======================");
				if(cookie.getName().equals("check")) {
					ck = cookie;
				}
				
			}
		}
		
		if(ck == null) {
			Cookie cookie = new Cookie("check", null);
			cookie.setComment("중복체크");
			cookie.setMaxAge(60 * 60);
			
			cookie.setValue(String.valueOf(number));		
			response.addCookie(cookie);
			
			log.info("===================");
			log.info("cookie.getComment : {}", cookie.getComment());
			log.info("cookie.getPath : {}", cookie.getPath());
			log.info("cookie.getValue() : {}", cookie.getValue());
			log.info("===================");
		}else {
			log.info("============있을 때===============");
			log.info("ck.getComment() : {}", ck.getComment());
			log.info("ck.getValue() : {}", ck.getValue());
			log.info("ck.getPath : {}", ck.getPath());
			log.info("===========================");
			if(!ck.getValue().contains(String.valueOf(number))) {
				String str = ck.getValue() +"/" +number;
				ck.setValue(str);
				log.info("str : {}", str);
				response.addCookie(new Cookie("check", str));
				
				
			}
			log.info("ck.setValue : {}", ck.getValue());
		}
		
		

		ModelAndView mav = new ModelAndView();

		BoardDTO board = boardService.selectById(number);


		mav.addObject("board", board);
		mav.setViewName("board/detail");

		return mav;
	}


	@GetMapping("/modify/{number}")
	public ModelAndView modify(@PathVariable long number) {

		ModelAndView mav = new ModelAndView();
		BoardDTO board = boardService.selectById(number);

		mav.addObject("board", board);
		mav.setViewName("board/modify");

		return mav;
	}


	@PostMapping("/modify")
	public ModelAndView modify(@ModelAttribute BoardDTO board) {


		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/detail/" + board.getNumber());

		boardService.updateById(board);

		return mav;
	}


	@ResponseBody
	@PostMapping("/delete")
	public int delete(@RequestParam long number) {


		int result = boardService.deleteById(number);

		return result;
	}


	@GetMapping("login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();

		if(request.getSession().getAttribute("user") == null) {
			request.getSession().setAttribute("user", user);
		}

		mav.setViewName("redirect:/");
		return mav;
	}


	@GetMapping("logout")
	public ModelAndView logout(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.invalidate();
		}

		mav.setViewName("redirect:/");

		return mav;
	}
}

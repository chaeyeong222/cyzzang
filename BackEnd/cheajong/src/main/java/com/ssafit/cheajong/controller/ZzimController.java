package com.ssafit.cheajong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.cheajong.model.dto.Zzim;
import com.ssafit.cheajong.model.service.ZzimService;

@RestController
@RequestMapping("/zzimapi")
@CrossOrigin("*")
public class ZzimController {

	@Autowired
	ZzimService zService;

	@PostMapping("/zzim")
	public ResponseEntity<?> addZzim(@RequestBody Zzim zzim) {
		try {
			int res = zService.addZzim(zzim);
			if (res == 1)
				return new ResponseEntity<>(HttpStatus.CREATED);
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@DeleteMapping("/zzim/{zzimNum}")
	public ResponseEntity<?> deleteZzim(@PathVariable int zzimNum) {
		try {
			int res = zService.deleteZzim(zzimNum);
			if (res == 1)
				return new ResponseEntity<>(HttpStatus.ACCEPTED);
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<?> selectZzim(@PathVariable String userId) {
		try {
			List<Zzim> res = zService.selectByUserId(userId);
			if (res.size() > 0)
				return new ResponseEntity<List<Zzim>>(res, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<?> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("오류발생: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

package com.gdu.movie.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdu.movie.domain.MovieDTO;
import com.gdu.movie.mapper.MovieMapper;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieMapper movieMapper;
	
	
@Override
public List<MovieDTO> getAllmovies() {
	
	return movieMapper.selectAllMovies();
}

@Override
public void searchMovies(HttpServletRequest request) {

	
}
	
}

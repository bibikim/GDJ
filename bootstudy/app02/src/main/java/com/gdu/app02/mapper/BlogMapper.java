package com.gdu.app02.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app02.domain.BlogDTO;

@Mapper
public interface BlogMapper {
	public int selectBlogListCount();
	public List<BlogDTO> selectBlogListByMap(Map<String, Object> map);
	public int insertBlog(BlogDTO blog);
	public int updateHit(int blogNo);
	public BlogDTO selectBlogByNo(int blogNo);
	public int updateBlog(int blogNo);
	public int deleteBlogByNo(int blogNo);
}

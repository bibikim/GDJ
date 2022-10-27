package repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.Post;

public class PostDAO {

	private SqlSessionFactory factory;
	
	private static PostDAO dao = new PostDAO();
	private PostDAO() {
		
		try {
			
			String resource = "mybatis/config/mybatis-config.xml";
			InputStream in = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(in);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static PostDAO getInstance() {
		return dao;
	}
	
	String mapper = "mybatis.mapper.post.";
	
	// 목록보기
	public List<Post> selectAllPosts() {
		SqlSession ss = factory.openSession();
		List<Post> posts = ss.selectList(mapper + "selectAllPosts");
		ss.close();
		return posts;
	}
	
	// 게시글 카운팅
	public int selectAllCount() {
		SqlSession ss = factory.openSession();
		int count = ss.selectOne(mapper + "selectAllCount");
		ss.close();
		return count;
	}
	
	// 글 삽입
	public int insertPost(Post post) {
		SqlSession ss = factory.openSession(false);
		int result = ss.insert(mapper + "insertPost", post);
		if(result > 0) {
			ss.commit();
		} 
		ss.close();
		return result;
	}
	
	// 게시글 상세
	public Post selectPostByNo(int postNo) {
		SqlSession ss = factory.openSession();
		Post post = ss.selectOne(mapper + "selectPostByNo", postNo);
		ss.close();
		return post;
	}


}

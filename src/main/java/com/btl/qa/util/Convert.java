package com.btl.qa.util;

import java.util.ArrayList;
import java.util.List;

import com.btl.qa.dto.AnswerDTO;
import com.btl.qa.dto.CategoryDTO;
import com.btl.qa.dto.FollowedDTO;
import com.btl.qa.dto.LikeDTO;
import com.btl.qa.dto.QuestionDTO;
import com.btl.qa.dto.ReplyDTO;
import com.btl.qa.dto.UserDTO;
import com.btl.qa.entity.Answer;
import com.btl.qa.entity.Category;
import com.btl.qa.entity.Followed;
import com.btl.qa.entity.Like;
import com.btl.qa.entity.Question;
import com.btl.qa.entity.Reply;
import com.btl.qa.entity.User;

public class Convert {

//	User
	public static UserDTO toDTO(User user) {
		UserDTO dto = new UserDTO();

		dto.setCreatedBy(user.getCreatedBy());
		dto.setCreatedDate(user.getCreatedDate());
		dto.setFullName(user.getFullName());
		dto.setId(user.getId());
		dto.setModifiedBy(user.getModifiedBy());
		dto.setModifiedDate(user.getModifiedDate());
		
//		dto.setPassword(user.getPassword());
		dto.setRole(user.getRole());
		dto.setStatus(user.getStatus());
		dto.setUsername(user.getUsername());
		dto.setAvatar(user.getAvatar());
		
		dto.setAnswers(user.getAnswers().size());
		dto.setQuestions(user.getQuestions().size());

		return dto;
	}

	public static User toEntity(UserDTO dto) {
		User user = new User();

		user.setId(dto.getId());
		user.setCreatedBy(dto.getCreatedBy());
		user.setCreatedDate(dto.getCreatedDate());
		user.setFullName(dto.getFullName());
		user.setModifiedBy(dto.getModifiedBy());
		user.setModifiedDate(dto.getModifiedDate());
		user.setPassword(dto.getPassword());
		user.setRole(dto.getRole());
		user.setStatus(dto.getStatus());
		user.setUsername(dto.getUsername());
		user.setAvatar(dto.getAvatar());

		return user;
	}

//	Question
	public static QuestionDTO toDTO(Question question) {
		QuestionDTO dto = new QuestionDTO();

		dto.setId(question.getId());
		dto.setCreatedBy(question.getCreatedBy());
		dto.setCreatedDate(question.getCreatedDate());
		dto.setModifiedBy(question.getModifiedBy());
		dto.setModifiedDate(question.getModifiedDate());

		dto.setCategory_id(question.getCategory().getId());
		dto.setContent(question.getContent());
		dto.setTitle(question.getTitle());
		dto.setUser_id(question.getUser().getId());

		dto.setCategoryName(question.getCategory().getName());
		dto.setFullName(question.getUser().getFullName());
		dto.setAvatarLink(question.getUser().getAvatar());

		return dto;
	}

	public static Question toEntity(QuestionDTO dto) {
		Question question = new Question();

		question.setId(dto.getId());
		question.setCreatedBy(dto.getCreatedBy());
		question.setCreatedDate(dto.getCreatedDate());
		question.setModifiedBy(dto.getModifiedBy());
		question.setModifiedDate(dto.getModifiedDate());

//		question.setCategory(dto.getCategory_id());
		question.setContent(dto.getContent());
		question.setTitle(dto.getTitle());
//		question.setUser(dto.getUser_id());

		return question;
	}

//	Answer
	public static AnswerDTO toDTO(Answer answer) {
		AnswerDTO dto = new AnswerDTO();
		
		dto.setId(answer.getId());
		dto.setCreatedBy(answer.getCreatedBy());
		dto.setCreatedDate(answer.getCreatedDate());
		dto.setModifiedBy(answer.getModifiedBy());
		dto.setModifiedDate(answer.getModifiedDate());

		dto.setContent(answer.getContent());
		dto.setUserId(answer.getUser().getId());
		dto.setAvatarLink(answer.getUser().getAvatar());
		dto.setFullName(answer.getUser().getFullName());
		dto.setQuestionId(answer.getQuestion().getId());

		List<ReplyDTO> replyDtos = new ArrayList<>();
		List<Reply> replies = (List<Reply>) answer.getReplies();
		
		if (replies != null) {
			for (int i = 0; i < replies.size(); i++) {
				replyDtos.add(Convert.toDTO(replies.get(i)));
			}
		}
		
		dto.setReplies(replyDtos);
		
		return dto;
	}

	public static Answer toEntity(AnswerDTO dto) {

		Answer answer = new Answer();

		answer.setId(dto.getId());
		answer.setCreatedBy(dto.getCreatedBy());
		answer.setCreatedDate(dto.getCreatedDate());
		answer.setModifiedBy(dto.getModifiedBy());
		answer.setModifiedDate(dto.getModifiedDate());

		answer.setContent(dto.getContent());
		
		return answer;
	}

//	Reply
	public static ReplyDTO toDTO(Reply reply) {
		ReplyDTO dto = new ReplyDTO();

		dto.setId(reply.getId());
		dto.setCreatedBy(reply.getCreatedBy());
		dto.setCreatedDate(reply.getCreatedDate());
		dto.setModifiedBy(reply.getModifiedBy());
		dto.setModifiedDate(reply.getModifiedDate());

		dto.setContent(reply.getContent());
		dto.setUserId(reply.getUser().getId());
		dto.setAvatarLink(reply.getUser().getAvatar());
		dto.setFullName(reply.getUser().getFullName());
		dto.setAnswerId(reply.getAnswer().getId());

		return dto;
	}

	public static Reply toEntity(ReplyDTO dto) {
		Reply reply = new Reply();

		reply.setId(dto.getId());
		reply.setCreatedBy(dto.getCreatedBy());
		reply.setCreatedDate(dto.getCreatedDate());
		reply.setModifiedBy(dto.getModifiedBy());
		reply.setModifiedDate(dto.getModifiedDate());

		reply.setContent(dto.getContent());
//		setAnswer, setUser

		return reply;
	}
	
	public static CategoryDTO toDTO(Category category) {
		CategoryDTO dto = new CategoryDTO();
		dto.setName(category.getName());
		dto.setId(category.getId());
		dto.setQuestions(category.getQuestions().size());
		return dto;
	}

//	Followed
	public static FollowedDTO toDTO(Followed followed) {
		FollowedDTO dto = new FollowedDTO();
		dto.setId(followed.getId());
		dto.setCreatedBy(followed.getCreatedBy());
		dto.setCreatedDate(followed.getCreatedDate());
		dto.setModifiedBy(followed.getModifiedBy());
		dto.setModifiedDate(followed.getModifiedDate());
		dto.setUserId(followed.getUser().getId());
		dto.setFollowedId(followed.getUserFollowed().getId());
		dto.setFollowName(followed.getUserFollowed().getFullName());
		dto.setFollowAvatar(followed.getUserFollowed().getAvatar());
		return dto;
	}
	
	public static Followed toEntity(FollowedDTO dto) {
		
		Followed followed = new Followed();

		followed.setId(dto.getId());
		followed.setCreatedBy(dto.getCreatedBy());
		followed.setCreatedDate(dto.getCreatedDate());
		followed.setModifiedBy(dto.getModifiedBy());
		followed.setModifiedDate(dto.getModifiedDate());
		
		//Set user, follow
		
		return followed;
	}
	
//	Like 
	public static LikeDTO toDTO(Like like) {
		LikeDTO dto = new LikeDTO();
		dto.setId(like.getId());
		dto.setCreatedBy(like.getCreatedBy());
		dto.setCreatedDate(like.getCreatedDate());
		dto.setModifiedBy(like.getModifiedBy());
		dto.setModifiedDate(like.getModifiedDate());
		dto.setUserId(like.getUser().getId());
		dto.setAnswerId(like.getAnswer().getId());
		return dto;
	}
	
	public static Like toEntity(LikeDTO dto) {
		
		Like like = new Like();

		like.setId(dto.getId());
		like.setCreatedBy(dto.getCreatedBy());
		like.setCreatedDate(dto.getCreatedDate());
		like.setModifiedBy(dto.getModifiedBy());
		like.setModifiedDate(dto.getModifiedDate());
		
		//Set user, follow
		
		return like;
	}
	
}

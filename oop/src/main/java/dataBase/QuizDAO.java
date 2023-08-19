package dataBase;

import objects.Quiz;
import objects.Review;
import objects.questions.Question;

import java.util.List;

public interface QuizDAO {
    public int addQuiz(Quiz quiz);
    public boolean removeQuiz(int id);
    public List<Quiz> getQuizzesByAuthor(int author);
    public List<Quiz> getQuizByQuizName(String name);
    public Quiz getQuizByID(int id);
    public List<Quiz> getPopularQuizzes(int top);
    public List<Question> getQuestions(Quiz quiz);
    public List<Quiz> recentlyCreatedQuizzes(int num);
    public List<String> getTags(Quiz quiz);
    public List<Review> getReviews(Quiz quiz);
    public Double getRating(Quiz quiz);
    public List<Quiz> getTopRatedQuizzes(int top);
    public String getCategory(Quiz quiz);
}

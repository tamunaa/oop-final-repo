package dataBase.questionsDAOs;

import objects.questions.Question;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionsDAO {
    private final BasicDataSource basicDataSource;
    private QuestionDAOType questionDAOType;

    public QuestionsDAO(BasicDataSource basicDataSource) {
        this.basicDataSource = basicDataSource;
    }
    public void resetQuestionDAOTypeByQuestionType(String questionType) {
        switch (questionType) {
            case "QuestionResponse", "FillInTheBlank", "PictureResponse":
                questionDAOType = new QuestionResponseDAO(basicDataSource);
                break;
            case "Matching":
                questionDAOType = new MatchingDAO(basicDataSource);
                break;
            case "MultiAnswer":
                questionDAOType = new MultiAnswerDAO(basicDataSource);
                break;
            case "MultipleChoice", "MultipleChoiceWithMultipleAnswer":
                questionDAOType = new MultipleChoiceDAO(basicDataSource);
                break;

        }
    }

            public void resetQuestionDAOTypeByQuestionId (int questionId){
            String questionQuery = "SELECT question_type " +
                    "FROM QUESTIONS " +
                    "WHERE question_id = ?";
            try (Connection connection = basicDataSource.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(questionQuery);
                preparedStatement.setInt(1, questionId);
                ResultSet result = preparedStatement.executeQuery();
                if (result.next()) {
                    String questionType = result.getString("question_type");
                    resetQuestionDAOTypeByQuestionType(questionType);
                } else {
                    questionDAOType = null;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        public int addQuestion (Question question, int quizId){
            resetQuestionDAOTypeByQuestionType(question.getQuestionType());
            return questionDAOType.addQuestion(question, quizId);
        }
        public Question getQuestionByQuestionId (int questionId){
            resetQuestionDAOTypeByQuestionId(questionId);
            if (questionDAOType == null) {
                return null;
            }
            return questionDAOType.getQuestionByQuestionId(questionId);
        }

        public void removeQuestion (int questionId){
            String deleteQuestionQuery = "DELETE FROM QUESTIONS WHERE question_id = ?;";
            try (Connection connection = basicDataSource.getConnection();
                 PreparedStatement deleteQuestionStmt = connection.prepareStatement(deleteQuestionQuery)) {
                deleteQuestionStmt.setInt(1, questionId);
                deleteQuestionStmt.executeUpdate();
            } catch (SQLException e) { e.printStackTrace(); }
        }
}

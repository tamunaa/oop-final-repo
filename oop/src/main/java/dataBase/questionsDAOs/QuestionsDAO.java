package dataBase.questionsDAOs;

import dataBase.ConnectionPool;
import objects.questions.FillInTheBlank;
import objects.questions.PictureResponse;
import objects.questions.Question;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionsDAO {
    private final ConnectionPool pool;
    private QuestionDAOType questionDAOType;

    public QuestionsDAO(ConnectionPool pool) {
        this.pool = pool;
    }
    public void resetQuestionDAOTypeByQuestionType(String questionType) throws SQLException {
        switch (questionType) {
            case "QuestionResponse", "FillInTheBlank":
                questionDAOType = new QuestionResponseDAO(pool);
                break;
            case "Matching":
                questionDAOType = new MatchingDAO(pool);
                break;
            case "MultiAnswer":
                questionDAOType = new MultiAnswerDAO(pool);
                break;
            case "MultipleChoice", "MultipleChoiceWithMultipleAnswer", "AutoGeneratedQuestion":
                questionDAOType = new MultipleChoiceDAO(pool);
                break;
            case "PictureResponse":
                questionDAOType = new PictureResponseDAO(pool);
                break;
            case "Graded":
                questionDAOType = new GradedQuestionDAO(pool);
                break;
        }
    }

            public void resetQuestionDAOTypeByQuestionId (int questionId){
            String questionQuery = "SELECT question_type " +
                    "FROM QUESTIONS " +
                    "WHERE question_id = ?";
                Connection connection = pool.getConnection();

                try {
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
            }        finally{
                    pool.releaseConnection(connection);
                }
        }
        public int addQuestion (Question question, int quizId) throws SQLException {
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
            Connection connection = pool.getConnection();

            try (
                 PreparedStatement deleteQuestionStmt = connection.prepareStatement(deleteQuestionQuery)) {
                deleteQuestionStmt.setInt(1, questionId);
                deleteQuestionStmt.executeUpdate();
            } catch (SQLException e) { e.printStackTrace(); }
            finally{
                pool.releaseConnection(connection);
            }
        }
}

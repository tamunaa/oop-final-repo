package dataBase.questionsDAOs;

import objects.questions.*;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class MultipleChoiceDAO implements QuestionDAOType {
    private final BasicDataSource basicDataSource;

    public MultipleChoiceDAO(BasicDataSource basicDataSource) {
        this.basicDataSource = basicDataSource;
    }

    @Override
    public int addQuestion(Question question, int quizId) {
        int questionId = -1;
        String insertQuestionQuery = "INSERT INTO QUESTIONS (quiz_id, question_text, question_type, timer) VALUES (?, ?, ?, ?)";
        String insertOptionsQuery = "INSERT INTO MULTIPLE_CHOICE (question_id, option_text, is_answer) VALUES (?, ?, ?)";

        try (Connection connection = basicDataSource.getConnection();
             PreparedStatement insertQuestionStatement = connection.prepareStatement(insertQuestionQuery, PreparedStatement.RETURN_GENERATED_KEYS);
             PreparedStatement insertOptionsStatement = connection.prepareStatement(insertOptionsQuery)) {

            // Insert into QUESTIONS table
            insertQuestionStatement.setInt(1, quizId);
            insertQuestionStatement.setString(2, question.getQuestion());
            insertQuestionStatement.setInt(4, question.getTimer());
            insertQuestionStatement.setString(3, question.getQuestionType());
            insertQuestionStatement.executeUpdate();

            // Get the generated question_id for the newly inserted Question
            try (ResultSet generatedKeys = insertQuestionStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    questionId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Inserting Question failed, no ID obtained.");
                }
            }

            HashSet<String> correct_answers = new HashSet<>();
            for (String answer : question.getCorrectAnswers()) {
                correct_answers.add(answer);
            }
            // Insert into MULTIPLE_CHOICE table
            for (String option : question.getOptions()) {
                insertOptionsStatement.setInt(1, questionId);
                insertOptionsStatement.setString(2, option);
                insertOptionsStatement.setBoolean(3, correct_answers.contains(option));
                insertOptionsStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questionId;
    }

    @Override
    public Question getQuestionByQuestionId(int questionId) {
        String selectQuestionQuery = "SELECT * FROM QUESTIONS WHERE question_id = ?";
        String selectOptionsQuery = "SELECT * FROM MULTIPLE_CHOICE WHERE question_id = ?";
        try (Connection connection = basicDataSource.getConnection();
             PreparedStatement selectQuestionStatement = connection.prepareStatement(selectQuestionQuery);
             PreparedStatement selectOptionsStatement = connection.prepareStatement(selectOptionsQuery)) {

            // Get the question details from the QUESTIONS table
            selectQuestionStatement.setInt(1, questionId);
            try (ResultSet questionResultSet = selectQuestionStatement.executeQuery()) {
                if (questionResultSet.next()) {
                    String questionText = questionResultSet.getString("question_text");
                    int timer = questionResultSet.getInt("timer");

                    // Get the options from the MULTIPLE_CHOICE table
                    selectOptionsStatement.setInt(1, questionId);
                    try (ResultSet optionsResultSet = selectOptionsStatement.executeQuery()) {
                        List<String> optionsList = new ArrayList<>();
                        List<String> answersList = new ArrayList<>();
                        while (optionsResultSet.next()) {
                            String option = optionsResultSet.getString("option_text");
                            boolean isAnswer = optionsResultSet.getBoolean("is_answer");
                            if (isAnswer) {
                                answersList.add(option);
                            }
                            optionsList.add(option);

                        }

                        // Convert list to array
                        String[] optionsArray = optionsList.toArray(new String[0]);
                        String[] answersArray = answersList.toArray(new String[0]);
                        Question newQuestion = null;

                        switch (questionResultSet.getString("question_type")) {
                            case "MultipleChoice":
                                newQuestion = new MultipleChoice(questionText, optionsArray, answersArray[0]);
                                break;
                            case "MultipleChoiceWithMultipleAnswer":
                                newQuestion = new MultipleChoiceWithMultipleAnswer(questionText, optionsArray, answersArray);
                                break;
                        }
                        newQuestion.setQuestionId(questionId);
                        newQuestion.setTimer(timer);
                        return newQuestion;

                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
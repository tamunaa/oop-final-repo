package objects.questions;

import java.util.*;

import static java.util.Collections.sort;

public class AutoGeneratedQuestion implements Question {
    private String question;
    private List<String> options;
    private int correctOptionIndex;
    private int timer = 0;


    public AutoGeneratedQuestion(){
        question = new String();
        options = new ArrayList<>();
        generateQuestion();
    }

    @Override
    public int getTimer(){
        return timer;
    }

    @Override
    public void setTimer(int timer){
        this.timer = timer;
    }

    @Override
    public String getQuestion() {
        return question;
    }

    @Override
    public String[] getOptions() {
        return options.toArray(new String[options.size()]);
    }

    @Override
    public String[] getCorrectAnswers() {
        String[] answer = {options.get(correctOptionIndex)};
        return answer;
    }

    @Override
    public boolean isOrdered() {
        return false;
    }

    @Override
    public String getQuestionType() {
        return "AutoGeneratedQuestion";
    }

    @Override
    public int getQuestionId() {
        return -1;
    }

    @Override
    public void setQuestionId(int questionId) {

    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }

    @Override
    public int evaluate(String answer){
        if(answer.equals(options.get(correctOptionIndex)))return 1;
        else return 0;
    }

    @Override
    public int getNumFields(){
        return 4;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(question).append("\n");
        for (int i = 0; i < options.size(); i++) {
            stringBuilder.append(i + 1).append(". ").append(options.get(i)).append("\n");
        }
        return stringBuilder.toString();
    }


    // Autogenerated Question
    public void generateQuestion() {
        Random random = new Random();
        int questionType = random.nextInt(17); // Random number between 0 and 17 (17 types of probability questions)
        switch (questionType) {
            case 0:
                generateProbabilityOfSimpleEventQuestion();
                break;
            case 1:
                generateProbabilityOfComplementaryEventQuestion();
                break;
            case 2:
                generateProbabilityOfCombinedEventQuestion();
                break;
            case 3:
                generateMeanQuestion();
                break;
            case 4:
                generateMedianQuestion();
                break;
            case 5:
                generateModeQuestion();
                break;
            case 6:
                generateIntegralQuestion();
                break;
            case 7:
                generateAreaQuestion();
                break;
            case 8:
                generatePerimeterQuestion();
                break;
            case 9:
                generateUnionQuestion();
                break;
            case 10:
                generateIntersectionQuestion();
                break;
            case 11:
                generateAdditionQuestion();
                break;
            case 12:
                generateSubtractionQuestion();
                break;
            case 13:
                generateMultiplicationQuestion();
                break;
            case 14:
                generateDivisionQuestion();
                break;
            case 15:
                generateLimitQuestion();
                break;
            case 16:
                generateDerivativeQuestion();
            default:
                throw new IllegalStateException("Unknown question type");
        }
    }


    // Autogenerated Math Question: Addition
    public void generateAdditionQuestion() {
        Random random = new Random();
        int num1 = random.nextInt(100);
        int num2 = random.nextInt(100);
        int answer = num1 + num2;

        question = String.format("What is %d + %d?", num1, num2);

        generateOptions(Integer.toString(answer));
    }

    // Autogenerated Math Question: Subtraction
    public void generateSubtractionQuestion() {
        Random random = new Random();
        int num1 = random.nextInt(100);
        int num2 = random.nextInt(100);
        int answer = num1 - num2;

        question = String.format("What is %d - %d?", num1, num2);

        generateOptions(Integer.toString(answer));
    }

    // Autogenerated Math Question: Multiplication
    public void generateMultiplicationQuestion() {
        Random random = new Random();
        int num1 = random.nextInt(20);
        int num2 = random.nextInt(20);
        int answer = num1 * num2;

        question = String.format("What is %d * %d?", num1, num2);

        generateOptions(Integer.toString(answer));
    }

    // Autogenerated Math Question: Division
    public void generateDivisionQuestion() {
        Random random = new Random();
        int divisor = random.nextInt(9) + 1;
        int answer = random.nextInt(20) + 1;
        int num1 = divisor * answer;

        question = String.format("What is %d ÷ %d?", num1, divisor);

        generateOptions(Integer.toString(answer));
    }


    // Autogenerated Probability Question: Probability of a Simple Event
    private void generateProbabilityOfSimpleEventQuestion() {
        Random random = new Random();
        int favorableOutcomes = random.nextInt(6) + 1;
        int totalOutcomes = random.nextInt(10) + 6;

        question = String.format("What is the probability of getting a favorable outcome in an event with %d total outcomes and %d favorable outcomes?", totalOutcomes, favorableOutcomes);

        double probability = (double) favorableOutcomes / totalOutcomes;
        String formattedProbability = String.format("%.2f", probability);

        options.add(formattedProbability);
        options.add(String.format("%.2f", probability * 100));
        options.add(String.format("%.2f", (1.0 - probability)));
        options.add(String.format("%.2f", (1.0 - probability) * 100));

        correctOptionIndex = 0;
        shuffleOptions(formattedProbability);
    }

    // Autogenerated Probability Question: Probability of a Complementary Event
    private void generateProbabilityOfComplementaryEventQuestion() {
        Random random = new Random();
        int favorableOutcomes = random.nextInt(6) + 1;
        int totalOutcomes = random.nextInt(10) + 6;

        question = String.format("What is the probability of not getting a favorable outcome in an event with %d total outcomes and %d favorable outcomes?", totalOutcomes, favorableOutcomes);

        double probability = (double) favorableOutcomes / totalOutcomes;
        double complementaryProbability = 1.0 - probability;
        String formattedProbability = String.format("%.2f", complementaryProbability);

        options.add(formattedProbability);
        options.add(String.format("%.2f", complementaryProbability * 100));
        options.add(String.format("%.2f", probability));
        options.add(String.format("%.2f", probability * 100));

        correctOptionIndex = 0;
        shuffleOptions(formattedProbability);
    }

    // Autogenerated Probability Question: Probability of Combined Events
    private void generateProbabilityOfCombinedEventQuestion() {
        Random random = new Random();
        int favorableOutcomesEventA = random.nextInt(4) + 1; // Random number of favorable outcomes for event A between 1 and 4
        int totalOutcomesEventA = random.nextInt(6) + 5; // Random total number of outcomes for event A between 5 and 10
        int favorableOutcomesEventB = random.nextInt(4) + 1; // Random number of favorable outcomes for event B between 1 and 4
        int totalOutcomesEventB = random.nextInt(6) + 5; // Random total number of outcomes for event B between 5 and 10

        question = String.format("What is the probability of both events A and B occurring? (Event A: %d favorable outcomes out of %d, Event B: %d favorable outcomes out of %d)", favorableOutcomesEventA, totalOutcomesEventA, favorableOutcomesEventB, totalOutcomesEventB);

        double probabilityA = (double) favorableOutcomesEventA / totalOutcomesEventA;
        double probabilityB = (double) favorableOutcomesEventB / totalOutcomesEventB;
        double combinedProbability = probabilityA * probabilityB;
        String formattedProbability = String.format("%.2f", combinedProbability);

        options.add(formattedProbability);
        options.add(String.format("%.2f", combinedProbability * 100));
        options.add(String.format("%.2f", probabilityA + probabilityB));
        options.add(String.format("%.2f", (probabilityA + probabilityB) * 100));

        correctOptionIndex = 0;
        shuffleOptions(formattedProbability);
    }


    // Autogenerated Statistics Question: Mean
    private void generateMeanQuestion() {
        Random random = new Random();
        int numOfElements = random.nextInt(10)+1;

        List<Integer> data = new ArrayList<>();
        int sum = 0;

        for (int i = 0; i < numOfElements; i++) {
            int value = random.nextInt(101);
            data.add(value);
            sum += value;
        }

        question = String.format("What is the mean of the following data set? %s", data.toString());

        double mean = (double) sum / numOfElements;
        String formattedMean = String.format("%.2f", mean);

        options.add(formattedMean);
        options.add(String.format("%.2f", mean + 5));
        options.add(String.format("%.2f", mean - 5));
        options.add(String.format("%.2f", mean + 10));

        correctOptionIndex = 0;
        shuffleOptions(formattedMean);
    }

    // Autogenerated Statistics Question: Median
    private void generateMedianQuestion() {
        Random random = new Random();
        int numOfElements = random.nextInt(10) + 5;

        List<Integer> data = new ArrayList<>();

        for (int i = 0; i < numOfElements; i++) {
            int value = random.nextInt(101);
            data.add(value);
        }

        sort(data);
        question = String.format("What is the median of the following data set? %s", data.toString());

        double median;
        if (numOfElements % 2 == 0) {
            median = (double) (data.get(numOfElements / 2 - 1) + data.get(numOfElements / 2)) / 2;
        } else {
            median = data.get(numOfElements / 2);
        }

        String formattedMedian = String.format("%.2f", median);

        options.add(formattedMedian);
        options.add(String.format("%.2f", median + 5));
        options.add(String.format("%.2f", median - 5));
        options.add(String.format("%.2f", median + 10));

        correctOptionIndex = 0;
        shuffleOptions(formattedMedian);
    }

    // Autogenerated Statistics Question: Mode
    private void generateModeQuestion() {
        Random random = new Random();
        int numOfElements = random.nextInt(10) + 5;

        List<Integer> data = new ArrayList<>();

        for (int i = 0; i < numOfElements; i++) {
            int value = random.nextInt(10);
            data.add(value);
        }

        question = String.format("What is the mode of the following data set? %s", data.toString());

        int[] frequencies = new int[10];
        for (int value : data) {
            frequencies[value]++;
        }

        int maxFrequency = 0;
        List<Integer> modes = new ArrayList<>();
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > maxFrequency) {
                maxFrequency = frequencies[i];
                modes.clear();
                modes.add(i);
            } else if (frequencies[i] == maxFrequency) {
                modes.add(i);
            }
        }

        options.add(modes.toString());
        options.add(data.get(0).toString());
        options.add(data.get(numOfElements - 1).toString());
        options.add(String.valueOf(maxFrequency));

        correctOptionIndex = 0;
        shuffleOptions(modes.toString());
    }

    // Autogenerated Statistics Question: Standard Deviation
    private void generateStandardDeviationQuestion() {
        Random random = new Random();
        int numOfElements = random.nextInt(10) + 5;

        List<Integer> data = new ArrayList<>();
        int sum = 0;

        for (int i = 0; i < numOfElements; i++) {
            int value = random.nextInt(101);
            data.add(value);
            sum += value;
        }

        question = String.format("What is the standard deviation of the following data set? %s", data.toString());

        double mean = (double) sum / numOfElements;
        double sumOfSquaredDifferences = 0;
        for (int value : data) {
            sumOfSquaredDifferences += Math.pow(value - mean, 2);
        }
        double standardDeviation = Math.sqrt(sumOfSquaredDifferences / (numOfElements - 1));
        String formattedStandardDeviation = String.format("%.2f", standardDeviation);

        options.add(formattedStandardDeviation);
        options.add(String.format("%.2f", standardDeviation + 5));
        options.add(String.format("%.2f", standardDeviation - 5));
        options.add(String.format("%.2f", standardDeviation + 10));

        correctOptionIndex = 0;
        shuffleOptions(formattedStandardDeviation);
    }


    // Autogenerated Geometry Question: Area
    private void generateAreaQuestion() {
        Random random = new Random();
        int length = random.nextInt(10) + 5;
        int width = random.nextInt(10) + 5;

        question = String.format("What is the area of a rectangle with length %d and width %d?", length, width);

        int area = length * width;

        options.add(String.valueOf(area));
        options.add(String.valueOf(area + 10));
        options.add(String.valueOf(area - 10));
        options.add(String.valueOf(area * 2));

        correctOptionIndex = 0;
        shuffleOptions(String.valueOf(area));
    }

    // Autogenerated Geometry Question: Perimeter
    private void generatePerimeterQuestion() {
        Random random = new Random();
        int side1 = random.nextInt(10) + 5;
        int side2 = random.nextInt(10) + 5;
        int side3 = random.nextInt(10) + 5;

        question = String.format("What is the perimeter of a triangle with side lengths %d, %d, and %d?", side1, side2, side3);

        int perimeter = side1 + side2 + side3;

        options.add(String.valueOf(perimeter));
        options.add(String.valueOf(perimeter + 10));
        options.add(String.valueOf(perimeter - 10));
        options.add(String.valueOf(perimeter * 2));

        correctOptionIndex = 0;
        shuffleOptions(String.valueOf(perimeter));
    }

    // Autogenerated Set Theory Question: Union
    private void generateUnionQuestion() {
        Random random = new Random();
        int setSize = random.nextInt(5) + 3;

        Set<Integer> setA = generateRandomSet(setSize);
        Set<Integer> setB = generateRandomSet(setSize);

        question = String.format("What is the union of the sets A = %s and B = %s?", setToString(setA), setToString(setB));

        Set<Integer> unionSet = new HashSet<>(setA);
        unionSet.addAll(setB);

        options.add(setToString(unionSet));
        options.add(setToString(setA));
        options.add(setToString(setB));
        options.add(setToString(new HashSet<>()));

        correctOptionIndex = 0;
        shuffleOptions(setToString(unionSet));
    }

    // Autogenerated Set Theory Question: Intersection
    private void generateIntersectionQuestion() {
        Random random = new Random();
        int setSize = random.nextInt(5) + 3;

        Set<Integer> setA = generateRandomSet(setSize);
        Set<Integer> setB = generateRandomSet(setSize);

        question = String.format("What is the intersection of the sets A = %s and B = %s?", setToString(setA), setToString(setB));

        Set<Integer> intersectionSet = new HashSet<>(setA);
        intersectionSet.retainAll(setB);

        options.add(setToString(intersectionSet));
        options.add(setToString(setA));
        options.add(setToString(setB));
        options.add(setToString(new HashSet<>()));

        correctOptionIndex = 0;
        shuffleOptions(setToString(intersectionSet));
    }

    // Generate a random set of integers
    private Set<Integer> generateRandomSet(int size) {
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < size; i++) {
            set.add(random.nextInt(20) + 1);
        }
        return set;
    }

    // Convert a set to a string representation
    private String setToString(Set<Integer> set) {
        StringBuilder stringBuilder = new StringBuilder("{");
        for (int value : set) {
            stringBuilder.append(value).append(", ");
        }
        if (!set.isEmpty()) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }


    // Autogenerated Calculus Question: Limit
    private void generateLimitQuestion() {
        Random random = new Random();
        int xValue = random.nextInt(10) + 1;

        question = String.format("What is the limit of the function f(x) = (x^2 - 1) / (x - %d) as x approaches %d?", xValue, xValue);

        // Calculate the limit manually
        int limit = xValue + 1;

        options.add(String.valueOf(limit));
        options.add(String.valueOf(limit + 1));
        options.add(String.valueOf(limit - 1));
        options.add(String.valueOf(limit * 2));

        correctOptionIndex = 0;
        shuffleOptions(String.valueOf(limit));
    }

    // Autogenerated Calculus Question: Derivative
    private void generateDerivativeQuestion() {
        Random random = new Random();
        int coefficient = random.nextInt(5) + 1;
        int power = random.nextInt(4) + 1;

        question = String.format("What is the derivative of the function f(x) = %dx^%d?", coefficient, power);

        // Calculate the derivative manually
        int derivativeCoefficient = coefficient * power;
        int derivativePower = power - 1;

        String derivative = String.format("%dx^%d", derivativeCoefficient, derivativePower);

        options.add(derivative);
        options.add(String.format("%dx^%d", derivativeCoefficient + 1, derivativePower));
        options.add(String.format("%dx^%d", derivativeCoefficient - 1, derivativePower));
        options.add(String.format("%dx^%d", derivativeCoefficient, derivativePower + 1));

        correctOptionIndex = 0;
        shuffleOptions(derivative);
    }

    // Autogenerated Calculus Question: Integral
    private void generateIntegralQuestion() {
        Random random = new Random();
        int coefficient = random.nextInt(5) + 1;
        int power = random.nextInt(4) + 1;

        question = String.format("What is the integral of the function f(x) = %dx^%d?", coefficient, power);

        // Calculate the integral manually
        double integralCoefficient = (double) coefficient / (power + 1);
        int integralPower = power + 1;

        String integral = String.format("%.2fx^%d", integralCoefficient, integralPower);

        options.add(integral);
        options.add(String.format("%.2fx^%d", integralCoefficient + 1, integralPower));
        options.add(String.format("%.2fx^%d", integralCoefficient - 1, integralPower));
        options.add(String.format("%.2fx^%d", integralCoefficient, integralPower - 1));

        correctOptionIndex = 0;
        shuffleOptions(integral);
    }


    // Generate options for the question
    private void generateOptions(String correctAnswer) {
        options.add(String.valueOf(correctAnswer));

        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            int offset = random.nextInt(5) + 1;
            int option = Integer.valueOf(correctAnswer) + (i % 2 == 0 ? offset : -offset);
            options.add(String.valueOf(option));
        }

        shuffleOptions(correctAnswer);
    }

    private void shuffleOptions(String correctAnswer){
        // Shuffle the options
        java.util.Collections.shuffle(options);

        // Get the index of the correct answer
        correctOptionIndex = options.indexOf(correctAnswer);
    }
}

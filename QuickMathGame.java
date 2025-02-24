import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

        
public class QuickMathGame extends JFrame implements ActionListener{
    private ArrayList<Integer> listA = new ArrayList<Integer>(99);
    private ArrayList<Integer> listB = new ArrayList<Integer>(19);
    
    private int questionType = 1;
    // There are 12 types of questions
    // Refers to QtypeA1-6 and QtypeB1-6
    
    private ArrayList<Integer> ques = new ArrayList<Integer>();
    
    private int time = 120;
    
    private boolean gameStatus = false;
    
    private int input;
    
    private int sol;
    
    private int score = 0;
    
    String quesString = "";
    
    // Below are GUI componets
    
    JPanel gamePanel = new JPanel();
          
    JLabel quesLabel = new JLabel(quesString, SwingConstants.CENTER);
    Font labelFont = quesLabel.getFont();
        
    JLabel timerLabel = new JLabel(Integer.toString(time, SwingConstants.CENTER));
    
    JLabel result = new JLabel("Quick Math Game", SwingConstants.CENTER);
        
    JTextField textbox = new JTextField(5);
        
    JButton submit = new JButton("Submit");
    
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == submit){
            if (Integer.parseInt(textbox.getText()) == sol){
                score++;
                result.setText("Answer Correct! Score: " + score);
                textbox.setText("");
                RefreshQuestion();
            }
            else {
                result.setText("Answer Incorrect! Score: " + score);
                textbox.setText("");
            }
        }
    }
    
    public QuickMathGame(){
        super("Quick Math Game");
        gamePanel.setLayout(new GridLayout(5,1));
        
        gamePanel.add(quesLabel);
        gamePanel.add(timerLabel);
        gamePanel.add(result);
        gamePanel.add(textbox);
        gamePanel.add(submit);
        
        submit.addActionListener(this);
        
        add(gamePanel, BorderLayout.CENTER);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }
    
    public final void InitListA(){ // MUST RUN BEFORE EVERYTHING !!!!!!!!!!!!!!
        for (int i = 1; i < 100; i++){
            listA.add(i);
        }
    }
    
    public final void InitListB(){ // MUST RUN BEFORE EVERYTHING !!!!!!!!!!!!!!
        for (int i = 1; i < 20; i++){
            listB.add(i);
        }
    }
    
    public ArrayList<Integer> InitQtypeAPlusNumbers(){ // Returns a arraylist consisting 3 integers: numA, numB, ans
        
        Random rand = new Random();
        
        int numA = listA.get(rand.nextInt(listA.size())); // Randomly get number from 1 to 99
        int numB = listA.get(rand.nextInt(listA.size())); // Randomly get number from 1 to 99
        
        int ans = numA + numB; // Answer
        
        ArrayList<Integer> ques = new ArrayList<Integer>();
        ques.add(numA);
        ques.add(numB);
        ques.add(ans);
        
        return ques;
    }
    
    public ArrayList<Integer> InitQtypeAMinusNumbers(){ // Returns a arraylist consisting 3 integers: numA, numB, ans
        
        Random rand = new Random();
        
        int numA = listA.get(rand.nextInt(listA.size())); // Randomly get number from 1 to 99
        int numB = listA.get(rand.nextInt(listA.size())); // Randomly get number from 1 to 99
        int ans;
        if (numA > numB){
            ans = numA - numB; // Answer
        } else {
            ans = numB - numA; // Answer
        }
        
        ArrayList<Integer> ques = new ArrayList<Integer>();
        if (numA > numB){
            ques.add(numA);
            ques.add(numB);
            ques.add(ans);
        } else {
            ques.add(numB);
            ques.add(numA);
            ques.add(ans);
        }
        
        return ques;
    }
    
    public ArrayList<Integer> InitQtypeBTimesNumbers(){ // Returns a arraylist consisting 3 integers: numA, numB, ans
        
        Random rand = new Random();
        
        int numA = listB.get(rand.nextInt(listB.size())); // Randomly get number from 1 to 19
        int numB = listB.get(rand.nextInt(listB.size())); // Randomly get number from 1 to 19
        
        int ans = numA * numB; // Answer
        
        ArrayList<Integer> ques = new ArrayList<Integer>();
        ques.add(numA);
        ques.add(numB);
        ques.add(ans);
        
        return ques;
    }

    public void DecideQuestionType(){ // change question type randomly from range 1 to 12
        Random rand = new Random();
        questionType = rand.nextInt(12) + 1;
    }
    
    public void RefreshQuestion(){
        DecideQuestionType();
        switch (questionType){
            case 1:
                ques = InitQtypeAPlusNumbers();
                quesString = ques.get(0) + " + " + ques.get(1) + " = ???";
                sol = ques.get(2);
                break;
            case 2:
                ques = InitQtypeAPlusNumbers();
                quesString = "??? + " + ques.get(1) + " = " + ques.get(2);
                sol = ques.get(0);
                break;
            case 3:
                ques = InitQtypeAPlusNumbers();
                quesString = ques.get(0) + " + ??? = " + ques.get(2);
                sol = ques.get(1);
                break;
            case 4:
                ques = InitQtypeAMinusNumbers();
                quesString = ques.get(0) + " - " + ques.get(1) + " = ???";
                sol = ques.get(2);
                break;
            case 5:
                ques = InitQtypeAMinusNumbers();
                quesString = "??? - " + ques.get(1) + " = " + ques.get(2);
                sol = ques.get(0);
                break;
            case 6:
                ques = InitQtypeAMinusNumbers();
                quesString = ques.get(0) + " - ??? = " + ques.get(2);
                sol = ques.get(1);
                break;
            case 7:
                ques = InitQtypeBTimesNumbers();
                quesString = ques.get(0) + " × " + ques.get(1) + " = ???";
                sol = ques.get(2);
                break;
            case 8:
                ques = InitQtypeBTimesNumbers();
                quesString = "??? × " + ques.get(1) + " = " + ques.get(2);
                sol = ques.get(0);
                break;
            case 9:
                ques = InitQtypeBTimesNumbers();
                quesString = ques.get(0) + " × ??? = " + ques.get(2);
                sol = ques.get(1);
                break;
            case 10:
                ques = InitQtypeBTimesNumbers();
                quesString = ques.get(2) + " ÷ " + ques.get(1) + " = ???";
                sol = ques.get(0);
                break;
            case 11:
                ques = InitQtypeBTimesNumbers();
                quesString = "??? ÷ " + ques.get(1) + " = " + ques.get(0);
                sol = ques.get(2);
                break;
            case 12:
                ques = InitQtypeBTimesNumbers();
                quesString = ques.get(2) + " ÷ ??? = " + ques.get(0);
                sol = ques.get(1);
                break;
        }
        quesLabel.setText(quesString);
        quesLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, 20));
        result.setFont(new Font(labelFont.getName(), Font.PLAIN, 20));
        timerLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, 20));
    }
    
    public void RefreshGame(){
        RefreshQuestion();
        result.setText("Quick Math Game");
        score = 0;
        time = 120;
    }
    
    public static void main(String[] args){
        QuickMathGame game = new QuickMathGame();
        game.InitListA();
        game.InitListB();
        
        game.RefreshQuestion();

        javax.swing.Timer timer = new javax.swing.Timer(1000, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                game.time -= 1;
                if (game.time > 0){
                    game.timerLabel.setText("Time Remaining: " + Integer.toString(game.time));
                    game.timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
                }
                if (game.time <= 0){
                    game.timerLabel.setText("Times Up");
                    game.timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    JOptionPane.showMessageDialog(null, "Your score: " + game.score);
                    game.RefreshGame();
                }
            }
        });
        timer.start();
        
        game.timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        game.setPreferredSize(new Dimension(300,250));
        game.pack();
        game.setResizable(false);
        game.setLocationRelativeTo(null);
        game.setVisible(true);
        // Below is used to test code using console input.
        // Below can be removed when GUI is used.
        Scanner scan = new Scanner(System.in);
        System.out.println(game.quesString);
        System.out.println("Please input your answer: ");
        game.input = scan.nextInt();
        if (game.input == game.sol){
            System.out.println("Answer Correct");
        }
        else {
            System.out.println("Answer Incorrect");
        }
    }
}
        
    

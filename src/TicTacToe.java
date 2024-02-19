package src;

import java.util.HashSet;
import java.util.Scanner;

public class TicTacToe {
    static HashSet<Integer> user_set = new HashSet<Integer>();
    static HashSet<Integer> computer_set = new HashSet<Integer>();

    //    To print the TicTacToe Board
    static void printBoard(char[][] gameBoard)   {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[0].length; j++) {
                System.out.print(gameBoard[i][j]);
            }
            System.out.println();
        }
    }


    static void positionHolder(char[][] gameBoard, int position, String user){
        char symbol = 'X';
        if(user.equals("You")){
            symbol = 'X';
            user_set.add(position);
        }
        else if (user.equals("Comp")){
            symbol ='O';
            computer_set.add(position);
        }


        switch (position){

            case 1:
                gameBoard[0][0] = symbol;
                break;

            case 2:
                gameBoard[0][2] = symbol;
                break;

            case 3:
                gameBoard[0][4] = symbol;
                break;

            case 4:
                gameBoard[2][0] = symbol;
                break;

            case 5:
                gameBoard[2][2] = symbol;
                break;

            case 6:
                gameBoard[2][4] = symbol;
                break;

            case 7:
                gameBoard[4][0] = symbol;
                break;

            case 8:
                gameBoard[4][2] = symbol;
                break;

            case 9:
                gameBoard[4][4] = symbol;
                break;

            default:
                System.out.println("Please enter a valid input");
                break;
        }
        System.out.println();
        printBoard(gameBoard);
    }

    static int gen_random(){
        int max = 9;
        int min = 1;
        int range = max-min+1;
        int result = (int) (Math.random() * range) + min;
        return result;
    }

    static String check_winner(){
        // For rows
        HashSet<Integer> row1 = new HashSet<Integer>();
        row1.add(1);row1.add(2); row1.add(3);
        HashSet<Integer> row2 = new HashSet<Integer>();
        row2.add(4);row2.add(5); row2.add(6);
        HashSet<Integer> row3 = new HashSet<Integer>();
        row3.add(7);row3.add(8); row3.add(9);

        // For coloums
        HashSet<Integer> col1 = new HashSet<Integer>();
        col1.add(1);col1.add(4); col1.add(7);
        HashSet<Integer> col2 = new HashSet<Integer>();
        col2.add(2);col2.add(5); col2.add(8);
        HashSet<Integer> col3 = new HashSet<Integer>();
        col3.add(3);col3.add(6); col3.add(9);

        // For diagonals
        HashSet<Integer> dia1 = new HashSet<Integer>();
        dia1.add(1);dia1.add(5); dia1.add(9);
        HashSet<Integer> dia2 = new HashSet<Integer>();
        dia2.add(3);dia2.add(5); dia2.add(7);

        HashSet<HashSet> set = new HashSet<HashSet>();
        set.add(row1);set.add(row2);set.add(row3);
        set.add(col1);set.add(col2);set.add(col3);
        set.add(dia1);set.add(dia2);

        for(HashSet c : set){
            if(user_set.containsAll(c))
                return "Congratulation!! You Won. ";
            else if(computer_set.containsAll(c))
                return "You Lost";
        }
        if(user_set.size()+computer_set.size()==9)
            return "Draw";

        return "";
    }



    public static void main(String[] args) {
        char gameBoard[][] = {
                {' ', '|', ' ', '|', ' '},
                {'-', '|', '-', '|', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '|', '-', '|', '-'},
                {' ', '|', ' ', '|', ' '}
        };
        printBoard(gameBoard);

        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.print("Please enter any number from 1 to 9 : ");
            int user_pos = sc.nextInt();
            while(user_set.contains(user_pos) || computer_set.contains(user_pos)){
                System.out.println();
                System.out.print("Retry, Enter Values from 1 to 9 : ");
                user_pos = sc.nextInt();
            }
            positionHolder(gameBoard,user_pos,"You");

            String result = check_winner();
            if(result.length() > 0){
                System.out.println(result);
                break;
            }

            int cpu_pos = gen_random();
            while(user_set.contains(cpu_pos) || computer_set.contains(cpu_pos)){
                cpu_pos = gen_random();
            }

            positionHolder(gameBoard,cpu_pos,"Comp");

            result = check_winner();
            if(result.length() > 0){
                System.out.println(result);
                break;
            }
        }
    }
}

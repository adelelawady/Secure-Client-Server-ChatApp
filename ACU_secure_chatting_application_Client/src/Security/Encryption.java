/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Security;

/**
 *
 * @author ELKONSOL
 */
public class Encryption {

    //ceaser encryption
    public static String CeaserEncrypt(String input, int key) {
        int index = 0;
        String EncryptedString = "";
        char[] ArrayChar = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        char[] inputArr = input.toCharArray();
        for (int i = 0; i < inputArr.length; i++) {
            for (int j = 0; j < ArrayChar.length; j++) {
                if (inputArr[i] == ArrayChar[j]) {
                    index = ((j + key) % 26);
                    EncryptedString += ArrayChar[index];

                }

            }

        }
        return EncryptedString;
    }

    // Play Fair
    public static class PlayFair {

        // print A matrix 

        public void printMatrix(char[][] Char_Matrix) {

            int rows = Char_Matrix.length;
            int columns = Char_Matrix[0].length;
            String str = "|\t";

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    str += Char_Matrix[i][j] + "\t";
                }

                System.out.println(str + "|");
                str = "|\t";
            }

        }

        // create Arraychar martix and remove KEY text from it
        public char[] RemoveWordFromArray(String Word) {

            String StrChar = "abcdefghijklmnopqrstuvwxyz";

            //replacing I from strchar to make it 25 char for matrix
            StrChar = StrChar.replace("j", "");

            //   StrChar=StrChar.replace("j", "i/j");
            char[] inputArrWord = Word.toCharArray();

            for (int i = 0; i < inputArrWord.length; i++) {
                StrChar = StrChar.replace(String.valueOf(inputArrWord[i]), "");
            }

            return StrChar.toCharArray();
        }

        public char[][] ImplementKey(String KEY) {
            char InputKey[][] = new char[5][5];

            // loop to make our matrix ready
            int current_index_KEY = 0;
            int current_index_non_KEY = 0;

            //get left array chars after rmoving KEY from it
            char[] Array_chars = RemoveWordFromArray(KEY);

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if ((current_index_KEY) < KEY.length()) {
                        // add plantext chars to matrix
                        InputKey[i][j] = KEY.charAt(current_index_KEY++);
                    } else {
                        InputKey[i][j] = Array_chars[current_index_non_KEY++];

                    }

                }
            }

            //  printMatrix(InputKey);
            //   HandelText(InputKey, "classx");
            return InputKey;
        }

        public int Get_Row_COL(char[][] Char_Matrix, char chr, String type) {
            int result = 0;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (Char_Matrix[i][j] == chr) {
                        if (type == "COL" || type == "") {
                            result = j;
                            break;
                        }
                        if (type == "ROW") {
                            result = i;
                            break;
                        }

                    }
                }
            }
            return result;
        }

        public String SHIFT(char[][] Char_Matrix, String TWO_CHARS) {

            //inti 
            String Result = "";
            char first, last;
            //inti
            char SHIFT_Result_first = '0', SHIFT_Result_last = '0';

            //split to chars into first and last
            first = TWO_CHARS.toString().toCharArray()[0];
            last = TWO_CHARS.toString().toCharArray()[1];

            //get current row and columan of first
            int first_ROW = Get_Row_COL(Char_Matrix, first, "ROW");
            int first_COL = Get_Row_COL(Char_Matrix, first, "COL");

            //get current row and columan of second
            int last_ROW = Get_Row_COL(Char_Matrix, last, "ROW");
            int last_COL = Get_Row_COL(Char_Matrix, last, "COL");

            // equal COL shift down 
            if (first_COL == last_COL) {

                if ((first_ROW + 1) < 5) {
                    SHIFT_Result_first = Char_Matrix[first_ROW + 1][first_COL];
                } else {
                    SHIFT_Result_first = Char_Matrix[0][first_COL];
                }

                if ((last_ROW + 1) < 5) {
                    SHIFT_Result_last = Char_Matrix[last_ROW + 1][last_COL];
                } else {
                    SHIFT_Result_last = Char_Matrix[0][last_COL];
                }

            }

            // equal ROW shift RIGHT 
            if (first_ROW == last_ROW) {

                if ((first_COL + 1) < 5) {
                    SHIFT_Result_first = Char_Matrix[first_ROW][first_COL + 1];
                } else {
                    SHIFT_Result_first = Char_Matrix[first_ROW][0];
                }

                if ((last_COL + 1) < 5) {
                    SHIFT_Result_last = Char_Matrix[last_ROW][last_COL + 1];
                } else {
                    SHIFT_Result_last = Char_Matrix[last_ROW][0];
                }

            }

            //opposite
            if (first_ROW != last_ROW && first_COL != last_COL) {

                // replacing rows
                SHIFT_Result_first = Char_Matrix[last_ROW][first_COL];
                SHIFT_Result_last = Char_Matrix[first_ROW][last_COL];

            }

            // update result with new chars
            Result += String.valueOf(SHIFT_Result_last);
            Result += String.valueOf(SHIFT_Result_first);

            return Result;
        }

        public String HandelText(String Key, String PlaneText) {

            char[][] Char_Matrix = ImplementKey(Key);

            // Split into Two Chars each
            //   PlaneText = "classx";
            //System.out.println( Get_Row_COL(Char_Matrix,(char) "g".toCharArray()[0],"COL"));
            String OutputValue = "";

            int Each_TWO_CHARS = 0;

            //split each two char in plantext
            while (Each_TWO_CHARS < PlaneText.toCharArray().length) {

                String first, next;
                first = String.valueOf(PlaneText.charAt(Each_TWO_CHARS));
                if ((Each_TWO_CHARS + 1) < PlaneText.toCharArray().length) {
                    next = String.valueOf(PlaneText.charAt(Each_TWO_CHARS + 1));
                } else {
                    next = "x";
                }

                if (first.equals(next)) {
                    next = "x";
                    Each_TWO_CHARS++;
                } else {
                    Each_TWO_CHARS += 2;
                }
                // group plan text as [ two chars , two chars , two chars ] in string 
                OutputValue += first + next + ",";

            }
            //                                 /\ 
            //                                 ||
            // remove last "," from plan text output
            String PlantextValue = OutputValue.substring(0, OutputValue.length() - 1);
 Display(Char_Matrix,PlantextValue); 
            /*
             ╔══╗╔═╗╔═╗╔═══╗╔═══╗╔═══╗╔════╗╔═══╗╔═╗─╔╗╔════╗
             ╚╣─╝║║╚╝║║║╔═╗║║╔═╗║║╔═╗║║╔╗╔╗║║╔═╗║║║╚╗║║║╔╗╔╗║
             ─║║─║╔╗╔╗║║╚═╝║║║─║║║╚═╝║╚╝║║╚╝║║─║║║╔╗╚╝║╚╝║║╚╝
             ─║║─║║║║║║║╔══╝║║─║║║╔╗╔╝──║║──║╚═╝║║║╚╗║║──║║──
             ╔╣─╗║║║║║║║║───║╚═╝║║║║╚╗──║║──║╔═╗║║║─║║║──║║──
             ╚══╝╚╝╚╝╚╝╚╝───╚═══╝╚╝╚═╝──╚╝──╚╝─╚╝╚╝─╚═╝──╚╝──
             
             use disably function to see output
            
             Display(Char_Matrix,PlantextValue);      
            
             |	s	e	c	u	r	|
             |	i	t	y	a	b	|
             |	d	f	g	h	k	|
             |	l	m	n	o	p	|
             |	q	v	w	x	z	|
             PLAN TEXT => classx
             cl => sn
             as => iu
             sx => uq
             classx
             sniuuq
             */
             //    for(String TWO_CHARS:OutputValue.split(",")){
            //        System.out.print(SHIFT(Char_Matrix, TWO_CHARS));
            //   }
            return ShiftALL(Char_Matrix, PlantextValue);
        }
        
        public String ShiftALL(char[][] Char_Matrix, String PLANTEXT_VLAUE) {
            String Result = "";
            String[] ValueSplit = PLANTEXT_VLAUE.split(",");
            for (int i = 0; i < PLANTEXT_VLAUE.split(",").length; i++) {
                Result += SHIFT(Char_Matrix, ValueSplit[i]);
            }
            return Result;
        }

        public void Display(char[][] Char_Matrix, String PLANTEXT_VLAUE) {
            // print matrix 
            printMatrix(Char_Matrix);

            //print planetext
            System.out.println("PLAN TEXT => " + PLANTEXT_VLAUE.replace(",", ""));

            //print planetext with encrytpted cipher text
            String[] ValueSplit = PLANTEXT_VLAUE.split(",");

            for (int i = 0; i < PLANTEXT_VLAUE.split(",").length; i++) {
                String SHIFT_OUTPUT = SHIFT(Char_Matrix, ValueSplit[i]);
                System.out.println(ValueSplit[i] + " => " + SHIFT_OUTPUT);

            }

            System.out.println(PLANTEXT_VLAUE.replace(",", ""));
            System.out.println(ShiftALL(Char_Matrix, PLANTEXT_VLAUE));

        }
        public void cryptanalysis() {
            // 
            
        }

        public String PlayFairEncrypt(String KEY, String PLANE_TEXT) {
            return HandelText(KEY, PLANE_TEXT);
        }
    }
}

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
public class Decryption {

    public static String CeaserDecrypt(String input, int key) {
        int index = 0;
        String EncryptedString = "";
        char[] ArrayChar = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'b', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        char[] inputArr = input.toCharArray();
        for (int i = 0; i < inputArr.length; i++) {
            for (int j = 0; j < ArrayChar.length; j++) {
                if (inputArr[i] == ArrayChar[j]) {
                    index = ((j - key) % 26);
                    EncryptedString += ArrayChar[index];

                }

            }

        }

        return EncryptedString;

    }

    public static class playfair {

        private String KeyWord = new String();
        private String Key = new String();
        private char matrix_arr[][] = new char[5][5];

        public void setKey(String k) {
            String K_adjust = new String();
            boolean flag = false;
            K_adjust = K_adjust + k.charAt(0);
            for (int i = 1; i < k.length(); i++) {
                for (int j = 0; j < K_adjust.length(); j++) {
                    if (k.charAt(i) == K_adjust.charAt(j)) {
                        flag = true;
                    }
                }
                if (flag == false) {
                    K_adjust = K_adjust + k.charAt(i);
                }
                flag = false;
            }
            KeyWord = K_adjust;
        }

        public void KeyGen() {
            boolean flag = true;
            char current;
            Key = KeyWord;
            for (int i = 0; i < 26; i++) {
                current = (char) (i + 97);
                if (current == 'j') {
                    continue;
                }
                for (int j = 0; j < KeyWord.length(); j++) {
                    if (current == KeyWord.charAt(j)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    Key = Key + current;
                }
                flag = true;
            }
        //    System.out.println(Key);
            matrix();
        }

        private void matrix() {
            int counter = 0;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    matrix_arr[i][j] = Key.charAt(counter);
                  //  System.out.printf("%s ", matrix_arr[i][j]);
                    counter++;
                }
              //  System.out.println("\n");
            }
        }

        private String format(String old_text) {
            int i = 0;
            int j = 0;
            int len = 0;
            String text = new String();
            len = old_text.length();
            for (int tmp = 0; tmp < len; tmp++) {
                if (old_text.charAt(tmp) == 'j') {
                    text = text + 'i';
                } else {
                    text = text + old_text.charAt(tmp);
                }
            }
            len = text.length();
            for (i = 0; i < len; i = i + 2) {
                if (text.charAt(i + 1) == text.charAt(i)) {
                    text = text.substring(0, i + 1) + 'x' + text.substring(i + 1);
                } else {
                }
            }
            return text;
        }

        private String[] Divid2Pairs(String new_string) {
            String Original = format(new_string);
            int size = Original.length();
            if (size % 2 != 0) {
                size++;
                Original = Original + 'x';
            }

            String x[] = new String[size / 2];
            int counter = 0;
            for (int i = 0; i < size / 2; i++) {
                x[i] = Original.substring(counter, counter + 2);
               // System.out.println(x[i]);
                counter = counter + 2;
            }
            return x;
        }

        public int[] GetDiminsions(char letter) {
            int[] key = new int[2];
            if (letter == 'j') {
                letter = 'i';
            }
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (matrix_arr[i][j] == letter) {
                        key[0] = i;
                        key[1] = j;
                        break;
                    }
                }
            }
            return key;
        }

        public String Encript(String Source) {
            //System.out.println("Encription Start");
            String src_arr[] = Divid2Pairs(Source);
            String Code = new String();
            char one;
            char two;
            int part1[] = new int[2];
            int part2[] = new int[2];
            for (int i = 0; i < src_arr.length; i++) {
                one = src_arr[i].charAt(0);
                two = src_arr[i].charAt(1);
                part1 = GetDiminsions(one);
                part2 = GetDiminsions(two);
                if (part1[0] == part2[0]) {
                    if (part1[1] < 4) {
                        part1[1]++;
                    } else {
                        part1[1] = 0;
                    }
                    if (part2[1] < 4) {
                        part2[1]++;
                    } else {
                        part2[1] = 0;
                    }
                } else if (part1[1] == part2[1]) {
                    if (part1[0] < 4) {
                        part1[0]++;
                    } else {
                        part1[0] = 0;
                    }

                    if (part2[0] < 4) {
                        part2[0]++;
                    } else {
                        part2[0] = 0;
                    }
                } else {
                    int temp = part1[1];
                    part1[1] = part2[1];
                    part2[1] = temp;
                }
                Code = Code + matrix_arr[part1[0]][part1[1]] + matrix_arr[part2[0]][part2[1]];
            }

       //     System.out.println(Code);
            return Code;
        }

        public String Decript(String Code) {
         //   System.out.println("Decription Start");
            String Original = new String();
            String src_arr[] = Divid2Pairs(Code);
            char one;
            char two;
            int part1[] = new int[2];
            int part2[] = new int[2];
            for (int i = 0; i < src_arr.length; i++) {
                one = src_arr[i].charAt(0);
                two = src_arr[i].charAt(1);
                part1 = GetDiminsions(one);
                part2 = GetDiminsions(two);

                if (part1[0] == part2[0]) {
                    if (part1[1] > 0) {
                        part1[1]--;
                    } else {
                        part1[1] = 4;
                    }
                    if (part2[1] > 0) {
                        part2[1]--;
                    } else {
                        part2[1] = 4;
                    }
                } else if (part1[1] == part2[1]) {
                    if (part1[0] > 0) {
                        part1[0]--;
                    } else {
                        part1[0] = 4;
                    }
                    if (part2[0] > 0) {
                        part2[0]--;
                    } else {
                        part2[0] = 4;
                    }
                } else {
                    int temp = part1[1];
                    part1[1] = part2[1];
                    part2[1] = temp;
                }
                Original = Original + matrix_arr[part1[0]][part1[1]] + matrix_arr[part2[0]][part2[1]];
            }
        //    System.out.println(Original);
            return Original;
        }

    }

}
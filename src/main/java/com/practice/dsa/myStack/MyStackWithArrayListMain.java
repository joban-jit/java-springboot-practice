package com.practice.dsa.myStack;

public class MyStackWithArrayListMain {
    public static void main(String[] args) {
        System.out.println(isBalancedParentheses("(()())()"));
        System.out.println(isBalancedParentheses("()()"));
        System.out.println(isBalancedParentheses("("));

    }

    public static String reverse(String s){
        char[] charArray = s.toCharArray();
        MyStackWithArrayList<Character> characterMyStackWithArrayList = new MyStackWithArrayList<>();
        for( char c : charArray){
            characterMyStackWithArrayList.push(c);
        }
        char[] reversedCharArray = new char[characterMyStackWithArrayList.size()];
        for(int i=0;i< reversedCharArray.length;i++){
            reversedCharArray[i] = characterMyStackWithArrayList.pop();

        }
        return new String(reversedCharArray);

    }

    public static boolean isBalancedParentheses(String parentheses){
        char[] charArray = parentheses.toCharArray();
        MyStackWithArrayList<Character> characterMyStackWithArrayList = new MyStackWithArrayList<>();
        char openingBracket = '(';
        char closedBracket = ')';
        for (char c : charArray) {
            if (openingBracket == c) {
                characterMyStackWithArrayList.push(c);
            }
            if (closedBracket == c) {
                if (characterMyStackWithArrayList.pop() == null) {
                    return false;
                }

            }


        }
        return characterMyStackWithArrayList.isEmpty();
    }

    //3, 1, 4, 2
    public static void sortStack(MyStackWithArrayList<Integer> stack){
        MyStackWithArrayList<Integer> tempStack = new MyStackWithArrayList<>();
        while(!stack.isEmpty()){
            Integer mainPop = stack.pop();
            while(!tempStack.isEmpty() && tempStack.peek()>mainPop){
                stack.push(tempStack.pop());
            }
            tempStack.push(mainPop);
        }
        while(!tempStack.isEmpty()){
            stack.push(tempStack.pop());
        }
    }
}

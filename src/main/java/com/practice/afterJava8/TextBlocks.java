package com.practice.afterJava8;

public class TextBlocks {
    /*
    A text block is a String object and as a result, shares the same properties as String object(immutable and interned)
    A text block begins with 3 double-quotes characters followed by newline i.e """"
    - text block cannot be put on one line
    - text of a text block cannot follow the """
     */


    public static void main(String[] args) {
        String tbnName = """
                Sean Kennedy""";
        String sName = "Sean Kennedy";

        // 1. a text block is a string object (immutable and interned)
        System.out.println(sName.equals(tbnName));// true
        System.out.println(sName == tbnName);//true
        // 2. String methods can be applied to text blocks
        System.out.println(tbnName.substring(5)); // kennedy

        // 3. a text blocks starts with """ followed by a line terminator

        // 4. Embedded double quotes are not required in text blocks
        String sQuote = "Hamlet: \"There is nothing either good or bad, " +
                "but thinking makes it so\""; // on one line
        System.out.println(sQuote);
        //Hamlet: "There is nothing either good or bad, but thinking makes it so"
        String tbQuote = """
                Hamlet: "There is nothing either good or bad, but thinking makes it so"
                """;
        System.out.println(tbQuote); // on one line
        //Hamlet: "There is nothing either good or bad, but thinking makes it so"

        // 5. Depending on where you have placed the closing delimiter(the three double quotes), determines whether or not
        // you have a closing \n
        String tbBookTitle1 = """
                Java
                Memory
                Management
                """;
        // this will show as this
        //Java\n
        //Memory\n
        //Management\n - // newline at end
        System.out.println(tbBookTitle1);
        String tbBookTitle2 = """
                Java
                Memory
                Management""";
        // this will show as this
        //Java\n
        //Memory\n
        //Management - // NO newline at end

        // Spacing is determined by the closing delimiter position or the first non-space character, whichever is
        // encountered first
        // All spaces(known as incidental spaces) leading up to that position, are stripped from all lines in the text
        // block
        //Note: above algorithm only works, if the closing delimiter is on a line of its own.

        // using text block for example json object is very easy to write in multiline
        jsonTraditionalStyle();
        jsonTextBlockStyle();

    }

    public static void jsonTraditionalStyle() {
        String text = "{\n" +
                "    \"name\": \"John Doe\",\n" +
                "    \"age\": 30,\n" +
                "    \"address\": \"123 Main Street\"\n" +
                "}";
        System.out.println(text);

    }

    public static void jsonTextBlockStyle() {
        String text = """
                {
                    "name": "John Doe",
                    "age": 30,
                    "address": "123 Main Street"
                }"""; // to remove incidental spaces, put delimiter on its own line (meaning writing """ as same line where string ends)
        System.out.println(text);
        System.out.println(1234);
        //{
        //    "name": "John Doe",
        //    "age": 30,
        //    "address": "123 Main Street"
        //}
        //1234

        text = """
                {
                    "name": "John Doe",
                    "age": 30,
                    "address": "123 Main Street"
                }
             """; // here you can see we have put """ on next line but also this moved to left so now spaces will be
                    // different for final output can you can see (so placement of closing """ decides those spaces")
        System.out.println(text);
        System.out.println(1234);
        //    {
        //       "name": "John Doe",
        //       "age": 30,
        //       "address": "123 Main Street"
        //   }
        //
        //1234

    }


}

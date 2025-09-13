/*
================================================================================
|| THEORY: UNDERSTANDING STRINGS IN JAVA
================================================================================

1. WHAT IS A STRING?
   - A String in Java is an object that represents a sequence of characters. For example, "hello" is a string of 5 characters.
   - It is NOT a primitive type like `int` or `char`. It is a class, specifically `java.lang.String`.
   - Because it's a class, it comes with many useful built-in methods for manipulation.

2. IMMUTABILITY: THE MOST IMPORTANT CONCEPT
   - A Java String object is **immutable**, which means its state (the sequence of characters) cannot be changed after it is created.
   - When you perform an operation that seems to modify a string, like converting it to uppercase, Java does NOT change the original string.
   - Instead, it creates a **new** String object in memory with the modification and returns a reference to that new object.

   Why is it immutable?
   - **Security**: String parameters are often used for file paths, network connections, etc. If they could be changed after a security check, it would create a vulnerability.
   - **Thread Safety**: Because they can't be changed, strings are inherently thread-safe. They can be shared among multiple threads without any risk of data corruption.
   - **Caching & Efficiency**: The Java Virtual Machine (JVM) maintains a special memory area called the "String Constant Pool". When you create a string literal (e.g., `String s = "abc";`), the JVM checks the pool. If the string already exists, it returns a reference to the existing one instead of creating a new object. This saves memory. Immutability makes this pooling possible.

3. STRING CREATION: LITERAL vs. `new` KEYWORD
   - **String Literal**: `String s1 = "Java";`
     - This is the most common way.
     - The JVM checks the String Constant Pool. If "Java" is already there, `s1` points to it. If not, it's created in the pool, and `s1` points to it.
     - If you then do `String s2 = "Java";`, `s1` and `s2` will point to the **exact same object** in memory.

   - **`new` Keyword**: `String s3 = new String("Java");`
     - This syntax **always** creates a new String object in the main memory area (the heap), regardless of whether "Java" is in the String Pool or not.
     - If you do `String s4 = new String("Java");`, `s3` and `s4` will be **two different objects** in memory, even though their contents are the same.

4. COMPARING STRINGS: `==` vs. `.equals()`
   - **`==` operator**: This compares object references. It checks if two variables point to the **exact same object** in memory.
   - **`.equals()` method**: This compares the actual sequence of characters. It checks if the two strings have the **same content**.

   - Golden Rule: **ALWAYS use `.equals()` to compare the content of two strings.** Use `==` only if you genuinely need to know if two variables refer to the same memory location.

5. MUTABLE ALTERNATIVES: `StringBuilder` and `StringBuffer`
   - Since String is immutable, performing many modifications (e.g., building a string in a loop) is inefficient because it creates many temporary objects.
   - For these situations, Java provides mutable classes:
     - **`StringBuilder`**: The modern, preferred choice. It is fast because it is **not synchronized** (not thread-safe). Use it when you are building a string in a single thread.
     - **`StringBuffer`**: The older, legacy choice. It is slower because its methods **are synchronized** (thread-safe). Use it only if you need to share and modify a string among multiple threads.
*/

import java.util.Arrays;
// import java.util.StringJoiner;

public class StringExamples {

    public static void main(String[] args) {
        System.out.println("--- 1. String Creation, Interning, and Comparison ---");
        demonstrateCreationAndComparison();

        System.out.println("\n--- 2. Common Inspection Methods ---");
        demonstrateInspectionMethods();

        System.out.println("\n--- 3. String Manipulation (Creating NEW Strings) ---");
        demonstrateManipulation();

        System.out.println("\n--- 4. Splitting and Joining Strings ---");
        demonstrateSplitAndJoin();

        System.out.println("\n--- 5. Formatting Strings ---");
        demonstrateFormatting();

        System.out.println("\n--- 6. Using StringBuilder for Efficient Manipulation ---");
        demonstrateStringBuilder();
    }

    public static void demonstrateCreationAndComparison() {
        // Using literals - these point to the same object in the String Constant Pool
        String s1 = "Hello";
        String s2 = "Hello";

        // Using the 'new' keyword - these create two different objects in the heap
        String s3 = new String("Hello");
        String s4 = new String("Hello");

        System.out.println("s1 = \"Hello\"; s2 = \"Hello\";");
        System.out.println("s3 = new String(\"Hello\"); s4 = new String(\"Hello\");");

        // Comparing with '==' (checks for same memory location)
        System.out.println("\nComparing with '==' (reference comparison):");
        System.out.println("  s1 == s2: " + (s1 == s2)); // true, both refer to the same pool object
        System.out.println("  s1 == s3: " + (s1 == s3)); // false, one is in the pool, one is in the heap

        // Comparing with '.equals()' (checks for same content)
        System.out.println("\nComparing with '.equals()' (content comparison):");
        System.out.println("  s1.equals(s2): " + s1.equals(s2)); // true
        System.out.println("  s1.equals(s3): " + s1.equals(s3)); // true
        System.out.println("  s3.equals(s4): " + s3.equals(s4)); // true
        
        System.out.println("\nComparing with '.compareTo()' (lexicographical comparison):");
        System.out.println("  \"A\".compareTo(\"B\"): " + "A".compareTo("B")); // negative value
        System.out.println("  \"B\".compareTo(\"A\"): " + "B".compareTo("A")); // positive value
        System.out.println("  \"A\".compareTo(\"A\"): " + "A".compareTo("A")); // zero
        System.out.println("  \"Apple\".compareToIgnoreCase(\"apple\"): " + "Apple".compareToIgnoreCase("apple")); // zero
    }

    public static void demonstrateInspectionMethods() {
        String text = "Java is a powerful language.";

        System.out.println("Original text: \"" + text + "\"");
        System.out.println("  Length: " + text.length());
        System.out.println("  Character at index 5: " + text.charAt(5)); // 'i'
        System.out.println("  Does it contain 'powerful'? " + text.contains("powerful")); // true
        System.out.println("  Does it start with 'Java'? " + text.startsWith("Java")); // true
        System.out.println("  Does it end with '.'? " + text.endsWith(".")); // true
        System.out.println("  Index of first 'a': " + text.indexOf('a')); // 1
        System.out.println("  Index of last 'a': " + text.lastIndexOf('a')); // 20
        System.out.println("  Is the string empty? " + text.isEmpty()); // false
    }

    public static void demonstrateManipulation() {
        String original = "   Learning Java   ";
        System.out.println("Original: \"" + original + "\"");

        // NOTE: None of these methods change the 'original' string. They all return a new one.
        String upper = original.toUpperCase();
        System.out.println("toUpperCase(): \"" + upper + "\"");

        String lower = original.toLowerCase();
        System.out.println("toLowerCase(): \"" + lower + "\"");

        // trim() removes leading and trailing whitespace
        String trimmed = original.trim();
        System.out.println("trim(): \"" + trimmed + "\"");

        // substring() extracts a part of the string
        String substring = trimmed.substring(0, 8); // from index 0 up to (but not including) 8
        System.out.println("substring(0, 8): \"" + substring + "\"");

        // replace() replaces all occurrences
        String replaced = trimmed.replace("Java", "Programming");
        System.out.println("replace('Java', 'Programming'): \"" + replaced + "\"");

        // concatenation
        String concatenated = trimmed.concat(" is fun!");
        System.out.println("concat(): \"" + concatenated + "\"");
        String concatenatedWithPlus = trimmed + " is great!"; // '+' operator also works
        System.out.println("'+' operator: \"" + concatenatedWithPlus + "\"");

        // Confirming the original is unchanged
        System.out.println("\nFinal check, Original is still: \"" + original + "\"");
    }

    public static void demonstrateSplitAndJoin() {
        String csvData = "apple,banana,orange,grape";
        System.out.println("CSV String: \"" + csvData + "\"");

        // split() breaks a string into an array of strings based on a delimiter (regex)
        String[] fruits = csvData.split(",");
        System.out.println("Split by ',': " + Arrays.toString(fruits));

        // String.join() is a static method to join an array/iterable into a single string
        String joinedString = String.join(" | ", fruits);
        System.out.println("Joined with ' | ': \"" + joinedString + "\"");
    }

    public static void demonstrateFormatting() {
        String name = "Alice";
        int age = 30;
        double balance = 12345.67;

        // String.format() creates a formatted string using placeholders
        // %s for String, %d for integer, %.2f for float/double with 2 decimal places
        String formatted = String.format("User: %s, Age: %d, Balance: $%.2f", name, age, balance);
        System.out.println(formatted);
    }

    public static void demonstrateStringBuilder() {
        // Inefficient way using String in a loop (creates many objects)
        String inefficient = "";
        for (int i = 0; i < 5; i++) {
            inefficient += i;
        }
        System.out.println("Inefficient concatenation with '+': " + inefficient);

        // Efficient way using StringBuilder
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            sb.append(i); // Appends to the internal buffer, no new objects created in the loop
        }
        System.out.println("Efficient concatenation with StringBuilder: " + sb.toString());
        
        // Other useful StringBuilder methods
        sb.insert(0, "Numbers: "); // Insert at a specific index
        System.out.println("After insert(0, ...): " + sb.toString());
        
        sb.delete(0, 9); // Delete a range of characters
        System.out.println("After delete(0, 9): " + sb.toString());
        
        sb.reverse(); // Reverse the sequence
        System.out.println("After reverse(): " + sb.toString());
    }
}
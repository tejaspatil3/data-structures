// File: EnumSetComprehensiveGuide.java

/*
 * ======================================================================================
 * | THEORY: A Deep Dive into EnumSet                                                   |
 * ======================================================================================
 *
 * ## 1. Core Concept: The Ultra-Efficient Set for Enums ⚡
 *
 * EnumSet is a specialized `Set` implementation designed exclusively for working with
 * elements of a single `enum` type. It is the most efficient and compact way to store
 * a collection of enum constants in Java.
 *
 * Unlike `HashSet` or `LinkedHashSet`, it does **not** use a hash table. Internally, it is
 * most often represented as a **bit vector** (similar to a `BitSet`).
 *
 * **Analogy: A Set of Light Switches**
 * Imagine a switch panel where each switch corresponds to one constant in your `enum`.
 * If a constant is in the `EnumSet`, its corresponding switch is flipped "ON" (its bit is 1).
 * If it's not in the set, the switch is "OFF" (its bit is 0).
 * 
 * This structure makes all operations, like adding an element (flipping a switch on) or
 * checking for an element (checking a switch's state), extremely fast.
 *
 * ## 2. Key Characteristics
 *
 * - **Exclusively for Enums**: It can only hold elements from a single `enum` type, which is
 * specified at the time of creation. This provides compile-time type safety.
 * - **High Performance**: All basic operations (`add`, `remove`, `contains`) execute in
 * constant time **O(1)**. It is significantly faster than `HashSet`.
 * - **Extremely Compact**: The bit vector representation uses a trivial amount of memory.
 * An `EnumSet` with up to 64 enum constants can be stored in a single `long` (64 bits).
 * - **Naturally Ordered**: The set always stores and iterates over its elements in their
 * **natural order**, which is the order they are declared in the `enum` definition itself.
 * - **No `null`s Allowed**: It does not permit `null` elements. Attempting to add `null`
 * will result in a `NullPointerException`.
 * - **No Public Constructor**: You cannot create an `EnumSet` using `new EnumSet<>()`.
 * You must use one of its static factory methods.
 * - **Not Synchronized**: It is not thread-safe and must be synchronized externally if used
 * in a multi-threaded environment.
 *
 * ## 3. When to Use EnumSet? 🤔
 *
 * You should **always** prefer `EnumSet` over any other `Set` implementation (like `HashSet`
 * or `TreeSet`) when you need to store a collection of enum constants. Its performance and
 * memory advantages are substantial. It is the perfect tool for representing a collection
 * of states, options, or flags.
 *
 * **Common Scenarios:**
 * - **User Permissions**: Representing a user's roles, such as `EnumSet.of(Role.ADMIN, Role.EDITOR)`.
 * - **Configuration Flags**: Storing a set of active features, like `EnumSet.of(Feature.ENABLE_LOGGING, Feature.USE_CACHE)`.
 * - **Days of the Week**: Defining a weekly schedule, for example, `EnumSet.range(Day.MONDAY, Day.FRIDAY)` for workdays.
 * - **Font Styles**: Representing text formatting, like `EnumSet.of(Style.BOLD, Style.ITALIC)`.
 *
 *
 * ## 4. Table of Factory Methods & Basic Operations
 *
 * ### Factory Methods (for creating an EnumSet)
 * | Method Signature                            | Description                                                    |
 * |---------------------------------------------|----------------------------------------------------------------|
 * | `EnumSet.noneOf(Class<E> elementType)`      | Creates an empty set for the specified enum type.              |
 * | `EnumSet.allOf(Class<E> elementType)`       | Creates a set containing ALL constants of the specified enum.  |
 * | `EnumSet.of(E e1, E e2, ...)`               | Creates a set pre-populated with the specified elements.       |
 * | `EnumSet.range(E from, E to)`               | Creates a set containing all enums in the range from-to.       |
 * | `EnumSet.complementOf(EnumSet<E> s)`        | Creates a set containing all elements of the enum NOT in set `s`.|
 * | `EnumSet.copyOf(Collection<E> c)`           | Creates a set from an existing collection of enums.            |
 *
 * ### Instance Methods (Basic Operations)
 * The instance methods (`add`, `remove`, `contains`, etc.) are the same as any other `Set`.
 *
 */

import java.util.EnumSet;
// import java.util.Iterator;

/**
 * An enum representing the days of the week to be used in our examples.
 * The natural order is MONDAY, TUESDAY, ..., SUNDAY.
 */
enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

/**
 * This class provides practical, executable examples for every concept and
 * function of EnumSet discussed in the theory section above.
 */
public class EnumSetComprehensiveGuide {

    public static void main(String[] args) {

        System.out.println("--- 1. Creating EnumSets using Factory Methods ---");

        // `noneOf()`: Creates an empty set. This is the typical starting point.
        EnumSet<Day> schedule = EnumSet.noneOf(Day.class);
        System.out.println("1. Using noneOf(): " + schedule);

        // `allOf()`: Creates a set with every constant from the Day enum.
        EnumSet<Day> allDays = EnumSet.allOf(Day.class);
        System.out.println("2. Using allOf(): " + allDays);

        // `of()`: Creates a set with a specified list of elements.
        EnumSet<Day> weekend = EnumSet.of(Day.SATURDAY, Day.SUNDAY);
        System.out.println("3. Using of(): " + weekend);

        // `range()`: Creates a set with elements between two specified constants (inclusive).
        EnumSet<Day> workDays = EnumSet.range(Day.MONDAY, Day.FRIDAY);
        System.out.println("4. Using range(): " + workDays);
        System.out.println("--------------------------------------------------\n");


        System.out.println("--- 2. Basic Operations: `add()`, `remove()`, `contains()` ---");
        EnumSet<Day> meetingDays = EnumSet.noneOf(Day.class);
        meetingDays.add(Day.MONDAY);
        meetingDays.add(Day.WEDNESDAY);
        meetingDays.add(Day.FRIDAY);
        System.out.println("Initial meeting days: " + meetingDays);

        // remove()
        meetingDays.remove(Day.FRIDAY);
        System.out.println("After removing FRIDAY: " + meetingDays);

        // contains()
        System.out.println("Does it contain MONDAY? " + meetingDays.contains(Day.MONDAY)); // true
        System.out.println("Does it contain TUESDAY? " + meetingDays.contains(Day.TUESDAY)); // false
        System.out.println("--------------------------------------------------\n");


        System.out.println("--- 3. Natural Order Iteration ---");
        EnumSet<Day> someDays = EnumSet.of(Day.SUNDAY, Day.TUESDAY, Day.FRIDAY, Day.MONDAY);
        System.out.println("Set as created: " + someDays);
        System.out.println("Iterating (guaranteed natural enum order):");
        // The output will be MONDAY, TUESDAY, FRIDAY, SUNDAY, not the order they were added.
        for (Day d : someDays) {
            System.out.println(" -> " + d);
        }
        System.out.println("--------------------------------------------------\n");


        System.out.println("--- 4. Bulk Operation: `complementOf()` ---");
        // Let's find the days that are NOT workdays.
        EnumSet<Day> notWorkDays = EnumSet.complementOf(workDays);
        System.out.println("Work days were: " + workDays);
        System.out.println("Complement of work days (i.e., the weekend): " + notWorkDays);
        System.out.println("--------------------------------------------------\n");


        System.out.println("--- 5. `null` is Not Allowed ---");
        try {
            // This line would throw a NullPointerException if uncommented.
            // meetingDays.add(null);
            System.out.println("Attempting to add null would throw a NullPointerException.");
        } catch (NullPointerException e) {
            System.out.println("Caught expected exception: " + e);
        }
        System.out.println("--------------------------------------------------\n");


        System.out.println("--- 6. Practical Use Case: User Permissions ---");
        enum Permission { READ, WRITE, EXECUTE, DELETE }

        // Assign basic permissions to a regular user.
        EnumSet<Permission> userPermissions = EnumSet.of(Permission.READ, Permission.EXECUTE);
        System.out.println("User permissions: " + userPermissions);

        // Assign all permissions to an admin user.
        EnumSet<Permission> adminPermissions = EnumSet.allOf(Permission.class);
        System.out.println("Admin permissions: " + adminPermissions);

        // Check if user has a specific permission.
        if (userPermissions.contains(Permission.WRITE)) {
            System.out.println("User can write files.");
        } else {
            System.out.println("User cannot write files.");
        }
        System.out.println("--------------------------------------------------\n");
    }
}
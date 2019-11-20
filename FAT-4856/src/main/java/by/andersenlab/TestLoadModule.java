package by.andersenlab;

/**
 * Test class module for runtime load in program.
 */
public class TestLoadModule extends StaticClassForLoad {
    @Override
    public String toString() {
        return "test Module Version 5 \n" +
                "Count is: " + (counter++) + "\n";
    }
}

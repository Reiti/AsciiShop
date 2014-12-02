/**
 * User: Michael Reitgruber
 * Date: 18.11.2014
 * Time: 13:45
 */
public class AsciiStack {
    private int increment;
    private AsciiImage[] stack;
    private int stackPointer;

    public AsciiStack(int increment) {
        this.increment = increment;
        stack = new AsciiImage[increment];
        stackPointer = -1;
    }

    public int capacity() {
        return stack.length;
    }

    public boolean empty() {
        return stackPointer == -1;
    }

    /**
     * Pops one value from the stack
     * @return the popped value
     */
    public AsciiImage pop() {
        if(empty())
            return null;
        AsciiImage val = peek();
        if(stackPointer+increment < stack.length)
            shrink();
        stack[stackPointer--] = null;
        return val;
    }

    public AsciiImage peek() {
        if(empty())
            return null;
        return stack[stackPointer];
    }

    /**
     * Pushes a value onto the stack
     * @param img the pushed value
     */
    public void push(AsciiImage img) {
        stack[++stackPointer] = img;
        if((stackPointer + increment) > stack.length)
            grow();
    }

    /**
     * Grows the size of the array used to represent the stack
     */
    private void grow() {
        if(stack.length < increment)
            return;
        AsciiImage[] newStack = new AsciiImage[stack.length+increment];
        System.arraycopy(stack,0,newStack,0,stack.length);
        stack = newStack;
    }

    /**
     * Shrinks the size of the array used to represent the stack
     */
    private void shrink() {
        if(stack.length == increment)
            return;
        AsciiImage[] newStack  = new AsciiImage[stack.length-increment];
        System.arraycopy(stack,0,newStack,0,newStack.length);
        stack = newStack;
    }

    /**
     *
     * @return the size of the stack
     */
    public int size() {
        return stackPointer+1;
    }

}


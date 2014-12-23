/**
 * User: Michael Reitgruber
 * Date: 18.11.2014
 * Time: 13:45
 */
public class AsciiStack {
    private class AsciiStackNode {
        int size=0;
        private AsciiStackNode next = null;
        private AsciiImage image = null;

        public AsciiStackNode(AsciiImage img, AsciiStackNode next) {
            this.image = img;
            this.next = next;
            //set size of current list to size of list after this element + 1
            if(next != null)
                size = next.size+1;
        }

        public AsciiImage getImage() {
            return image;
        }

        public AsciiStackNode getNext() {
            return next;
        }

        public int size() {
            return size;
        }


    }

    private AsciiStackNode head = null;
    public AsciiStack() {
    }

    public boolean empty() {
        return head==null;
    }

    /**
     *
     * @return null if stack is empty, else the topmost element, the element is then removed
     */
    public AsciiImage pop() {
        if(empty())
            return null;
        AsciiImage image = head.getImage();
        head = head.next;
        return image;
    }

    /**
     *
     * @return null if stack is empty, else the topmost element
     */
    public AsciiImage peek() {
        if(empty())
            return null;
        return head.getImage();
    }

    /**
     * Adds a new image to the undo stack
     * @param img The AsciiImage to be added
     */
    public void push(AsciiImage img) {
        head = new AsciiStackNode(img,head);
    }

    /**
     * @return the size of the undo stack
     */
    public int size() {
        if(empty())
            return 0;
        return head.size();
    }

}


public class Stack {
    private final int CARDS_IN_A_DECK = 52;

    private Node top;

    public Stack() {
        top = null;
    }

    public Stack(Object data) {
        top = new Node(data);
    }

    public boolean isEmpty() {
        return (top == null);
    }

    public Node getTop() {
        return top;
    }

    public void pop() {
        top = top.getNext();
    }

    public void push(Object data) {
        Node newNode = new Node(data);
        newNode.setNext(top);
        top = newNode;
    }

    /* Better way to do this? */
    public Card[] cardsToArray() {
        Card[] stackArray = new Card[CARDS_IN_A_DECK - 1];
        Node currentCard = top;
        int i = 0;

        while (currentCard.getNext() != null) {
            stackArray[i] = (Card)currentCard.getData();
            currentCard = currentCard.getNext();
            i++;
        }

        return stackArray;
    }

    public void emptyStack() {
        while (!isEmpty()) {
            pop();
        }
    }

    @Override
    public String toString() {
        StringBuffer finalString = new StringBuffer();
        Node currentNode = top;

        if (isEmpty()) {
            return "Stack is empty!";
        }
        
        while (currentNode.getNext() != null) {
            finalString.append(currentNode.getData().toString() + "\t");
            currentNode = currentNode.getNext();
        }

        return finalString.toString();
    }
}

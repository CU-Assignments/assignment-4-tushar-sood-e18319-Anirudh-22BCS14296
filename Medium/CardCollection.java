import java.util.*;

class CardCollection {
    private Map<String, List<String>> cardMap;

    public CardCollection() {
        cardMap = new HashMap<>();
    }

    public void addCard(String symbol, String card) {
        cardMap.putIfAbsent(symbol, new ArrayList<>());
        cardMap.get(symbol).add(card);
    }

    public Collection<String> getCardsBySymbol(String symbol) {
        return cardMap.getOrDefault(symbol, Collections.emptyList());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CardCollection collection = new CardCollection();

        collection.addCard("Hearts", "Ace of Hearts");
        collection.addCard("Hearts", "King of Hearts");
        collection.addCard("Spades", "Queen of Spades");
        collection.addCard("Diamonds", "Jack of Diamonds");
        collection.addCard("Clubs", "10 of Clubs");

        System.out.print("Enter the symbol (e.g., Hearts, Spades): ");
        String symbol = scanner.nextLine();

        Collection<String> cards = collection.getCardsBySymbol(symbol);
        
        if (cards.isEmpty()) {
            System.out.println("No cards found for symbol: " + symbol);
        } else {
            System.out.println("Cards for " + symbol + ": " + cards);
        }

        scanner.close();
    }
}
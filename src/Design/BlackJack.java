package Design;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Tin on 7/18/16.
 */
public class BlackJack {
    enum Suit{
        SPADES(4), HEARTS(3), DIAMONDS(2), CLUBS(1);
        int priority;
        Suit(int priority){
            this.priority = priority;
        }
    }
    class Card{
        Suit suit;
        int point;
        Card(Suit suit, int point){
            this.suit = suit;
            this.point = point;
        }
        @Override
        public String toString(){
            return suit.name().substring(0,1)+"/"+point;
        }
    }
    class CardDeck{
        final int NUM_OF_CARDS_IN_ONE_SET = 52;
        int curNCard;
        List<Card> cards;
        CardDeck(){
            cards = new ArrayList<>();
            addOneSet();
        }
        public void addOneSet(){
            int index = 0;
            for(Suit suit : Suit.values()){
                for(int i=1;i<=13;i++){
                    cards.add(new Card(suit, i));
                }
            }
        }
        public void shuffle(){
            Random random = new Random();
            for(int i=cards.size()-1;i>=0;i--){
                int swapIndex = random.nextInt(i+1);
                if(swapIndex!=i){
                    Card temp = cards.get(i);
                    cards.set(i, cards.get(swapIndex));
                    cards.set(swapIndex, temp);
                }
            }
        }
        public int numCard(){
            return cards.size();
        }
        public Card deal(){
            if(numCard()==0) return null;
            return cards.remove(cards.size()-1);
        }
    }
    class CardHand{
        static final int NUM_OF_SLOTS = 5;
        Card[] hand;
        int sum;
        int curNCard;
        int nAce;
        public int getSum() {
            return sum;
        }
        CardHand(){
            hand = new Card[NUM_OF_SLOTS];
            sum = 0;
            curNCard = 0;
            nAce = 0;
        }
        public boolean isFull(){
            return curNCard==NUM_OF_SLOTS;
        }
        public void assignCard(Card card){
            if(isFull()) return;
            hand[curNCard++] = card;
            sum += card.point>=10?10:card.point;
            if(card.point==1) nAce++;
        }
        public int getHeighestPoint(){
            int res = sum;
            for(int i=1;i<=nAce;i++){
                if(res+10<=21) res += 10;
                else break;
            }
            return res;
        }
        String print(boolean reveal){
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            for(int i = 0;i<hand.length;i++){
                if(hand[i]==null) sb.append("null, ");
                else if(!reveal && i==0) sb.append("XXX, ");
                else sb.append(hand[i]+", ");
            }
            sb.append("]");
            return sb.toString();
        }
        public boolean hasBroken(){
            return sum>21;
        }

    }
    class Player{
        String name;
        CardHand cardHand;
        int point;
        Player(String name){
            this.name = name;
            cardHand = new CardHand();
            point = 0;
        }
        void print(boolean reveal){
            System.out.println(name+": "+cardHand.print(reveal));
        }
        public void reset(){
            cardHand = new CardHand();
            point = 0;
        }
        public void finalize(){
            if(cardHand.hasBroken()) point = -1;
            else if(cardHand.isFull()) point = 21;
            else {
                point = cardHand.getHeighestPoint();
            }
        }
    }

    CardDeck cardDeck;
    Player dealer;
    List<Player> players;
    BlackJack(){
        cardDeck = new CardDeck();
        dealer = new Player("Dealer");
        players = new ArrayList<>();
    }
    public List<Player> getPlayers() {
        return players;
    }
    public void registerPlayer(String name){
        players.add(new Player(name));
    }
    public void firstDeal(){
        if(cardDeck.numCard()<(players.size()+1)*CardHand.NUM_OF_SLOTS){
            cardDeck.addOneSet();
            cardDeck.shuffle();
        }
        for(Player player : players){
            player.cardHand.assignCard(cardDeck.deal());
            player.cardHand.assignCard(cardDeck.deal());
        }
        dealer.cardHand.assignCard(cardDeck.deal());
        dealer.cardHand.assignCard(cardDeck.deal());
    }
    public void print(boolean reveal){
        dealer.print(reveal);
        System.out.println("-----");
        for(Player player : players){
            player.print(reveal);
        }
    }
    public void dealPlayer(Player player){
        player.cardHand.assignCard(cardDeck.deal());
    }
    public void checkDealer(){
        while(!dealer.cardHand.isFull() &&
                !dealer.cardHand.hasBroken() &&
                dealer.cardHand.getHeighestPoint()<17){
            dealPlayer(dealer);
        }
        dealer.finalize();
    }
    public void decideWinners(){
        System.out.println("===>");
        boolean dealerWin = true;
        for(Player player : players){
            if(player.point>dealer.point){
                System.out.println(player.name+" won!");
                dealerWin = false;
            }
        }
        if(dealerWin){
            System.out.println(dealer.name+" won!");
        }
    }
    public void reset(){
        dealer.reset();
        for(Player player : players) player.reset();
    }

    public static void main(String[] args) {
        BlackJack blackJack = new BlackJack();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Player1's name: ");
        String name1 = scanner.next();
        System.out.print("Player2's name: ");
        String name2 = scanner.next();
        blackJack.registerPlayer(name1);
        blackJack.registerPlayer(name2);

        boolean play = true;
        while(true) {
            blackJack.reset();
            blackJack.cardDeck.shuffle();
            blackJack.firstDeal();
            System.out.println();
            blackJack.print(false);
            for (Player player : blackJack.getPlayers()) {
                System.out.println();
                System.out.println(player.name + "'s turn ->");
                player.print(true);
                while (!player.cardHand.isFull()) {
                    System.out.print("Want more card? (y/n): ");
                    boolean want = scanner.next().equals("y");
                    if (want) {
                        blackJack.dealPlayer(player);
                        player.print(true);
                    } else break;
                    if (player.cardHand.hasBroken()) break;
                }
                player.finalize();
            }
            System.out.println();
            blackJack.checkDealer();
            blackJack.print(true);
            blackJack.decideWinners();
            System.out.print("Play again? (y/n)");
            play = scanner.next().equals("y");
        }
    }

}

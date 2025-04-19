import java.util.ArrayList;
import java.util.List;

// Observer Interface
interface Subscriber {
    void update(String article);
}

// Concrete Observer
class NewsSubscriber implements Subscriber {
    private String name;

    public NewsSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(String article) {
        System.out.println(name + " received article: " + article);
    }
}

// Subject Interface
interface NewsPublisher {
    void subscribe(Subscriber sub);
    void unsubscribe(Subscriber sub);
    void notifySubscribers(String article);
}

// Concrete Subject
class NewsAgency implements NewsPublisher {
    private List<Subscriber> subscribers = new ArrayList<>();

    @Override
    public void subscribe(Subscriber sub) {
        subscribers.add(sub);
        System.out.println(sub + " subscribed.");
    }

    @Override
    public void unsubscribe(Subscriber sub) {
        subscribers.remove(sub);
        System.out.println(sub + " unsubscribed.");
    }

    @Override
    public void notifySubscribers(String article) {
        for (Subscriber sub : subscribers) {
            sub.update(article);
        }
    }

    public void publishArticle(String article) {
        System.out.println("\nPublishing article: " + article);
        notifySubscribers(article);
    }
}

// Main class to demo
public class NewsApp {
    public static void main(String[] args) {
        NewsAgency agency = new NewsAgency();

        Subscriber alice = new NewsSubscriber("Alice");
        Subscriber bob = new NewsSubscriber("Bob");
        Subscriber charlie = new NewsSubscriber("Charlie");

        agency.subscribe(alice);
        agency.subscribe(bob);

        agency.publishArticle("Breaking News: Observer Pattern Simplified!");
        agency.publishArticle("Tech News: Java 21 Released!");

        agency.unsubscribe(bob);
        agency.subscribe(charlie);

        agency.publishArticle("World Update: New Policies Announced!");
    }
}

class Product {
    int id;
    String name;

    Product(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class Item {
    Product product;
    int quantity;
    Item prev;
    Item next;

    Item(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}

class ShoppingCart {
    Item head;

    ShoppingCart() {
    }

    boolean isEmpty() {
        return this.head == null;
    }

    void showAllItems() {
        if (isEmpty()) {
            System.out.println("No items in the cart");
        } else {
            Item node = this.head;
            while (node != null) {
                System.out.printf("%d - %s - %d\n", node.product.id, node.product.name, node.quantity);
                node = node.next;
            }
        }
    }

    void addItem(Product product, int quantity) {
        Item item = findItemByProductId(product.id);

        if (item != null) {
            item.quantity += quantity;
        } else {
            Item newItem = new Item(product, quantity);

            if (isEmpty()) {
                this.head = newItem;
            } else {
                newItem.next = this.head;
                this.head.prev = newItem;
                this.head = newItem;
            }
        }
    }

    void removeItem(Product product) {
        Item item = findItemByProductId(product.id);

        if (item != null) {
            Item prevNode = item.prev;
            Item nextNode = item.next;

            if (prevNode != null) {
                prevNode.next = nextNode;
            } else {
                this.head = nextNode;
            }

            if (nextNode != null) {
                nextNode.prev = prevNode;
            }
        }
    }

    private Item findItemByProductId(int productId) {
        Item node = this.head;
        while (node != null) {
            if (node.product.id == productId)
                return node;
            node = node.next;
        }
        return null;
    }
}

public class ShoppingCartProgram {
    public static void main(String args[]) {
        Product product1 = new Product(1, "Laptop");
        Product product2 = new Product(2, "Phone");

        ShoppingCart cart = new ShoppingCart();

        cart.addItem(product1, 2);
        cart.addItem(product2, 1);

        cart.showAllItems();

        cart.removeItem(product1);

        cart.showAllItems();
    }
}


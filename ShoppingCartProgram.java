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
        Item item = this.findItemByProductId(product.id);

        if (item != null) {
            item.quantity += quantity;
        } else {
            Item newItem = new Item(product, quantity);

            this.head.prev = newItem;
            newItem.next = this.head;
            this.head = newItem;
        }
    }

    void removeItem(Product product) {
        Item item = this.findItemByProductId(product.id);

        Item prevNode = item.prev, nextNode = item.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    private Item findItemByProductId(int productId) {
        Item node = this.head;
        while (node != null) {
            if (node.product.id == productId)
                return node;
        }

        return null;
    }

}

public class ShoppingCartProgram {
    public static void main(String args[]) {

    }
}

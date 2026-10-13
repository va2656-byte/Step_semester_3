
public class Problem5 {
    static class Node {
        int coefficient;
        int exponent;
        Node next;

        Node(int coefficient, int exponent) {
            this.coefficient = coefficient;
            this.exponent = exponent;
            this.next = null;
        }
    }

    static Node addPolynomials(Node p, Node q) {
        Node dummy = new Node(0, 0);
        Node tail = dummy;

        while (p != null && q != null) {
            if (p.exponent > q.exponent) {
                tail.next = new Node(p.coefficient, p.exponent);
                p = p.next;
                tail = tail.next;
            } else if (p.exponent < q.exponent) {
                tail.next = new Node(q.coefficient, q.exponent);
                q = q.next;
                tail = tail.next;
            } else {
                int sum = p.coefficient + q.coefficient;

                if (sum != 0) {
                    tail.next = new Node(sum, p.exponent);
                    tail = tail.next;
                }

                p = p.next;
                q = q.next;
            }
        }

        while (p != null) {
            tail.next = new Node(p.coefficient, p.exponent);
            tail = tail.next;
            p = p.next;
        }

        while (q != null) {
            tail.next = new Node(q.coefficient, q.exponent);
            tail = tail.next;
            q = q.next;
        }

        return dummy.next;
    }

    static void printPolynomial(Node head) {
        if (head == null) {
            System.out.println("0");
            return;
        }

        boolean first = true;

        while (head != null) {
            int c = head.coefficient;
            int e = head.exponent;
            int abs = Math.abs(c);

            if (!first) {
                System.out.print(c > 0 ? " + " : " - ");
            } else if (c < 0) {
                System.out.print("-");
            }

            if (e == 0 || abs != 1) {
                System.out.print(abs);
            }

            if (e > 0) {
                System.out.print("x");
                if (e > 1) {
                    System.out.print("^" + e);
                }
            }

            first = false;
            head = head.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Node p = new Node(5, 3);
        p.next = new Node(4, 2);
        p.next.next = new Node(2, 0);

        Node q = new Node(5, 2);
        q.next = new Node(-3, 1);
        q.next.next = new Node(1, 0);

        Node result = addPolynomials(p, q);
        printPolynomial(result);

        Node a = new Node(3, 2);
        a.next = new Node(1, 0);

        Node b = new Node(-3, 2);
        b.next = new Node(4, 1);

        Node result2 = addPolynomials(a, b);
        printPolynomial(result2);
    }
}

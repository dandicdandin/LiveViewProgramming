import java.util.Arrays;

class NimView {
    private final Nim nim;
    private final LiveView view;

    NimView(Nim nim) {
        Nim nim = this.nim;
        view = Clerk.view;
    }

    private int[] parseNim(Nim nim) {
        String[] lines = nim.toString().split("\\r?\\n"); // ein String-Array für jede Linie des Output-String-Texts aus der Rückgabe des Nims
        int[] rows = new int[lines.length]; // Zahl der Linien
        for(int i = 0; i < lines.length; i++) {
            line = lines[i].replaceAll("\\s", "");
            rows = line.length(); // Zahl der Hoelzschen in jeder Linie
        }

        return rows;
    }

    public static Turtle drawHoelzschen(Turle t, int size, number) {
        if (number == 0) return t;
        t.left(90).forward(size).penUp().backward(size).right(90).forward(size/4);
        drawHoelzschen(t, size, number-1);
        t.backward(size/4);
        return t;
    }

    public void show() {
        rows = parseNim(nim)
        Turtle t = new Turtle(500, 500);
        for(int n: rows) {
            drawHoelzschen(t, 20, n);
            t.right(90).forward(30).left(90);
        }
    }

    @java.lang.Override
    public java.lang.String toString() {
        show();
        return nim;
    }
}



Nim nm = Nim.of(5, 4, 3);
NimView zs = new NimView(Nim.of(5, 4, 3));

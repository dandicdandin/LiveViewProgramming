class NimView {
    private final Nim nim;

    NimView(Nim nim) {
        this.nim = nim;
    }

    private int[] parseNim(Nim nim) {
        String[] lines = nim.toString().replaceFirst("\\r?\\n", "").split("\\r?\\n"); // ein String-Array für jede Linie des Output-String-Texts aus der Rückgabe des Nims
        int[] rows = new int[lines.length]; // Zahl der Linien
        for(int i = 0; i < lines.length; i++) {
            rows[i] = lines[i].replaceAll("\\s", "").length(); // Zahl der Hoelzschen in jeder Linie
        }

        return rows;
    }

    public Turtle drawHoelzschen(Turtle t, double size, int number) { // Diese Methode zeichnet rekursiveweise die Hoelzschen mit angegebener Zahl.
        if (number == 0) return t; // Wenn keine Hoelzschen mehr zu zeichnen ist, ANKER.
        t.penDown().left(90).forward(size).penUp().backward(size).right(90).forward(size/4); // Zeichne Hoelzschen
        drawHoelzschen(t, size, number-1); // Zeiche naechste Hoelzschen
        t.backward(size/4); // geh zurueck zu Anfangspunkt mwahhh ^3^
        return t;
    }

    public void show() {
        Clerk.clear();
        int[] rows = parseNim(nim); // Parsiere nim to array
        double size = 100;
        int width = (int) (size*2 + 100);
        int height = (int) ((size*rows.length)+((rows.length-1)*size*0.1)+100);
        Turtle t = new Turtle(width, height).penUp().backward((int)((width-100)/2)).left(90).forward((int)((height-100)/2)-size).right(90);
        for(int n: rows) {
            System.out.println(n);
            drawHoelzschen(t, size, n);
            t.right(90).forward(size * 1.1f).left(90);
        }
    }

    @java.lang.Override
    public java.lang.String toString() {
        show();
        return nim.toString();
    }
}

NimView zs = new NimView(Nim.of(5, 4, 3));

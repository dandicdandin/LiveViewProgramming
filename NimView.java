import java.util.Arrays;

class NimView {
    private final Nim nim;
    private final LiveView liveView;

    NimView(Nim nim) {
        this.nim = nim;
        this.liveView = Clerk.view(); // Yeni bir LiveView başlatır
        show(); // Başlangıç durumu göster
    }

    // Nim durumunu tarayıcıda görsel olarak gösterir
    void show() {
        Clerk.clear(liveView); // Turtle ekranını temizle
        Turtle turtle = new Turtle(300, 300); // Başlangıç noktası

        for (int i = 0; i < nim.getRows().length; i++) {
            int sticks = nim.getRows()[i]; // Her satırdaki çubuk sayısını al
            for (int j = 0; j < sticks; j++) {
                // Her çubuk için bir çubuk çiz
                turtle.forward(20).right(90).forward(5).backward(5).left(90);
            }
            // Bir sonraki satıra geç
            turtle.backward(sticks * 20).right(90).forward(30).left(90); 
        }
        Clerk.write(liveView, turtle.toString()); // Güncellenmiş durumu göster
    }
}
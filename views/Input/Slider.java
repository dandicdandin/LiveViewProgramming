
class Slider implements Clerk {
    final String ID;
    LiveView view;
    Slider(LiveView view, double min, double max) {
        this.view = view;
        ID = Clerk.getHashID(this);
        Clerk.write(view, "<div><input type='range' id='slider" + ID + "' min='" + min + "' max='" + max + "' step='any'/> </div>");
        Clerk.script(view, "const slider" + ID + " = document.getElementById('slider" + ID + "');");
    }
    Slider attachTo(Consumer<String> delegate) {
        this.view.createResponseContext("/slider" + ID, delegate);
        Clerk.script(view, Text.fillOut(
            """
            slider${0}.addEventListener('input', (event) => {
                if (lock === true) return;
                lock = true;
                const value = event.target.value;
                console.log(`slider${0}: value = ${value}`);
                fetch('slider${0}', {
                   method: 'post',
                    body: value.toString()
                }).catch(console.error);
            });
            """, ID));
        return this;
    }
}

// https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/range
// Alternatives Event: slider\{ID}.addEventListener("change", (event) => {
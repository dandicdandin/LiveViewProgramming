package de.denkspuren.lvp.views.input;

import java.util.Map;
import java.util.function.Consumer;
import de.denkspuren.lvp.Clerk;
import de.denkspuren.lvp.LiveView;
import de.denkspuren.lvp.skills.text.Text;

public class Slider implements Clerk {
    public final String ID;
    LiveView view;
    public Slider(LiveView view, double min, double max) {
        this.view = view;
        ID = Clerk.getHashID(this);
        Clerk.write(view, "<div><input type='range' id='slider" + ID + "' min='" + min + "' max='" + max + "' step='any'/> </div>");
        Clerk.script(view, "const slider" + ID + " = document.getElementById('slider" + ID + "');");
    }
    public Slider attachTo(Consumer<String> delegate) {
        this.view.createResponseContext("/slider" + ID, delegate, ID);
        Clerk.script(view, Text.fillOut(
            """
            slider${0}.addEventListener('input', (event) => {
                if (locks.includes('${0}')) return;
                locks.push('${0}');
                const value = event.target.value;
                console.log(`slider${0}: value = ${value}`);
                fetch('slider${0}', {
                   method: 'post',
                    body: value.toString()
                }).catch(console.error);
            });
            """, Map.of("0", ID, "value", "${value}")));
        return this;
    }
}

// https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/range
// Alternatives Event: slider\{ID}.addEventListener("change", (event) => {
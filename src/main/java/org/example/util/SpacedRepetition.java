package org.example.util;
import org.example.model.Card;
import java.time.LocalDate;
public class SpacedRepetition {
   // Anwendung des Spaced Repetition Prinzips
    public static void apply(Card card, int grade) {
        double ef = card.getEfactor();
        if (ef < 1.3) ef = 1.3;

        if (grade >= 3) {
            card.setRepetition(card.getRepetition() + 1);
            if (card.getRepetition() == 1) {
                card.setInterval(1);
            } else if (card.getRepetition() == 2) {
                card.setInterval(6);
            } else {
                int nextInterval = (int) Math.round(card.getIntervall() * ef);
                card.setInterval(nextInterval);
            }

            // E-Faktor aktualisieren
            double newEF = ef + (0.1 - (5 - grade) * (0.08 + (5 - grade) * 0.02));
            card.setEfactor(Math.max(1.3, newEF));
        } else {
            card.setRepetition(0);
            card.setInterval(1);
        }

        card.setLastReviewed(LocalDate.now().toString());
    }
}

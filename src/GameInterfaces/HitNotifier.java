package GameInterfaces;
/**
 * @author Hadar Padael.
 * 212916753
 * an interface for hit notifier.
 */
public interface HitNotifier {
    // Add hl as a listener to hit events.
    void addHitListener(HitListener hl);
    // Remove hl from the list of listeners to hit events.
    void removeHitListener(HitListener hl);
}
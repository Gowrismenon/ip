package Mal.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


public class EventTaskTest {
    @Test
    public void dummyTest() {
        assertEquals(2,2);
    }

    @Test
    public void normalDeadline_success() {
        EventTask curr = new EventTask("name", "today", "tmr");
        assertEquals(curr.toString(), "[E][ ] name(from: today to: tmr)");

    }

    @Test
    public void dateDeadline_success() {
        Task cur = EventTask.taskify("name /from 2026-02-13 /to 2026-02-16");

        assertEquals(cur.toString(), "[E][ ] name (from: Feb 13 2026 to: Feb 16 2026)");
    }

    @Test
    public void taskify_isoDate_parsesAndFormats() {
        Task cur = EventTask.taskify("Project /from 2026-05-20 /to 2026-05-22");
        assertTrue(cur.toString().contains("May 20 2026"));
        assertTrue(cur.toString().contains("May 22 2026"));
    }

    @Test
    public void taskify_extraSpaces_handlesTrimming() {
        Task cur = EventTask.taskify("Meeting  /  2026-01-01  /  2026-01-02");
        assertNotNull(cur, "Should handle extra spaces without returning null");
        assertTrue(cur.toString().contains("Meeting"));
    }
    @Test
    public void taskify_emptyDescription_returnsTaskWithEmptyName() {
        Task cur = EventTask.taskify(" /2026-01-01 /2026-01-02");
        assertNotNull(cur);

    }

    @Test
    public void taskify_tooManySlashes_usesFirstThreeSegments() {
        Task cur = EventTask.taskify("Work /2026-01-01 /2026-01-02 /extra ignore");
        assertNotNull(cur);
    }

    @Test
    public void taskify_noSlashes_returnsNull() {
        Task cur = EventTask.taskify("just a random string with no slashes");
        assertNull(cur);
    }

    @Test
    public void missingFromTo_success() {
        Task curr = EventTask.taskify("name /to tmr");
        assertEquals(null, curr);

        Task now = EventTask.taskify("name /from tmr");
        assertEquals(null, now);

        Task third = EventTask.taskify("name /from /to");
        assertEquals(null, third);

    }


}
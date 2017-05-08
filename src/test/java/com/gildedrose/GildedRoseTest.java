package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GildedRoseTest {

    private Item item;
    private GildedRose app;

    @Before
    public void setUp() throws Exception {
        item = new Item("foo", 0, 0);
        final Item[] items = new Item[] {item};
        app = new GildedRose(items);
    }

    @Test
    public void updateQuality_doesNotAlterName() {
        item.name = "foo";
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    public void updateQuality_doesDecreaseSellIn() throws Exception {
        Item[] items = new Item[] { new Item("foo", 10, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, app.items[0].sellIn);
    }

    @Test
    public void updateQuality_doesDecreaseQuality() throws Exception {
        Item[] items = new Item[] { new Item("foo", 10, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, app.items[0].quality);
    }

    @Test
    public void updateQuality_withPassedSellIn_doesDecreaseQuality() throws Exception {
        Item[] items = new Item[] { new Item("foo", 0, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(8, app.items[0].quality);
    }

    @Test
    public void updateQuality_doesNotDecreaseQualityBelowZero() throws Exception {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    public void updateQuality_withNegativeQuality_doesNotDecreaseQuality() throws Exception {
        Item[] items = new Item[] { new Item("foo", 0, -10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-10, app.items[0].quality);
    }

    @Test
    public void updateQualityForAgedBrie_doesIncreaseQuality() throws Exception {
        Item[] items = new Item[] { new Item("Aged Brie", 10, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(11, app.items[0].quality);
    }

    @Test
    public void updateQualityForAgedBrieAfterSellIn_doesIncreaseQualityTwice() throws Exception {
        Item[] items = new Item[] { new Item("Aged Brie", 0, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(12, app.items[0].quality);
    }
}

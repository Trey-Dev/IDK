package com.treydev.idk.support;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ElementAffinity {
    private Element element;
    private double affinity;

    public Element getElement() {
        return element;
    }

    public double getAffinity() {
        return affinity;
    }

    public static ElementAffinity[] GetRandomAffinity() {
        return GetRandomAffinity(2);
    }

    public static ElementAffinity[] GetRandomAffinity(int count) {
        ArrayList<ElementAffinity> elementAffinities = new ArrayList<ElementAffinity>(count);
        for (int i = 0; i < count; i++) {
            ElementAffinity elementAffinity = new ElementAffinity();
            elementAffinity.element = Element.getRandomElement();
            elementAffinity.affinity = Random.nextDouble();
            elementAffinities.add(elementAffinity);
        }

        Collections.sort( elementAffinities, new Comparator<ElementAffinity>() {
            public int compare(ElementAffinity a, ElementAffinity b) {
                return a.affinity < b.affinity ? -1 : a.affinity > b.affinity ? 1 : 0;
            }});
        
        return elementAffinities.toArray(new ElementAffinity[elementAffinities.size()]);
    }

    public static Element GetRandomElementByAffinity(ElementAffinity[] elementAffinities) {
        int index = Random.nextInt(elementAffinities.length);
        return elementAffinities[index].element;
    }
}

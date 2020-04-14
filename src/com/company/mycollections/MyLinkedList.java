package com.company.mycollections;

import java.lang.reflect.Array;
import java.util.*;

public class MyLinkedList<E> implements ILinkedList<E>{

    private int size;
    private Element<E> first;
    private Element<E> last;

    @Override
    public void add(E element) {
        if(size != 0){
            Element<E> newLast = new Element<>(element, null, last);
            last.next = newLast;
            last = newLast;
        }else {
            first = new Element<>(element, null, null);
            last = first;
        }
        size++;
    }

    @Override
    public void add(int index, E element) {
        if(index == size){
            add(element);
        }else {
            if (checkRange(index)){
                Element<E> current = getNode(index);
                Element<E> newC = new Element<>(element, current, current.prev);

                if(current == first){
                    newC.next = current;
                    first = newC;
                }else {
                    current.prev.next = newC;
                }
                current.prev = newC;
                size++;
            }
        }
    }

    private boolean checkRange(int index){
        if(index >= 0 && index < size){
            return true;
        }else {
            throw new IndexOutOfBoundsException("index = "+index+" size = "+size);
        }
    }

    @Override
    public void clear() {

        for (Element<E> x = first; x != null; ) {
            Element<E> next = x.next;
            x.value = null;
            x.next = null;
            x.prev = null;
            x = next;
        }

        first = last = null;
        size = 0;

    }

    @Override
    public E get(int index) {
        if(checkRange(index)){
            return getNode(index).value;
        }else {
            return null;
        }
    }

    private Element<E> getNode(int index){

        if(index < (size >> 1)) {
            Element<E> eElement = first;
            for (int i = 0; i < index; i++)
                eElement = eElement.next;
            return eElement;
        }else {
            Element<E> eElement = last;
            for (int i = size - 1;i > index; i--) {
                eElement = eElement.prev;
            }
            return eElement;
        }
    }

    @Override
    public int indexOf(E element) {
        int index = 0;
        for (Element<E> x = first; x != null; x = x.next) {
            if (Objects.equals(element,x.value)) {
                return index;
            }
            index++;
        }

        return -1;
    }

    @Override
    public E remove(int index) {
        Element<E> remove = null;
        if (checkRange(index)){
            remove = getNode(index);

            if(remove == first){
                first = first.next;
                first.prev = null;
            }else {
                if (remove == last){
                    last = remove.prev;
                    last.next = null;
                }else {
                    remove.prev.next = remove.next;
                    remove.next.prev = remove.prev;
                }
            }
            size--;
        }

        return remove.value;
    }

    @Override
    public E set(int index, E element) {
        if(checkRange(index)){
            return getNode(index).value = element;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E[] toArray() {
        if(first == null){
            return null;
        }

        E[] array =  (E[]) Array.newInstance(first.value.getClass(),size);

        int index = 0;
        for(E o : this){
            array[index] = o;
            index++;
        }

        return array;
    }

    @Override
    public String toString() {
        String str = "MyLinkedList{";
        str+= Arrays.toString(toArray());
        str+="}";

        return str;
    }

    private static class Element<E> {
        E value;
        Element<E> next;
        Element<E> prev;

        Element(E current, Element<E> next, Element<E> prev) {
            this.value = current;
            this.next = next;
            this.prev = prev;
        }

        @Override
        public String toString() {
            return "Element{" +
                    "current=" + value +
                    ", next=" + (next == null ? "null" : next.value) +
                    ", prev=" + (prev == null ? "null" : prev.value) +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Element<?> element = (Element<?>) o;
            return Objects.equals(value, element.value) &&
                    Objects.equals(next, element.next) &&
                    Objects.equals(prev, element.prev);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, next, prev);
        }
    }

    private class MyIterator implements Iterator{
        Element cursor = first;

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public E next() {
            E returned = (E) cursor.value;
            cursor = cursor.next;
            return returned;
        }
    }

    @Override
    public Iterator iterator() {
        return new MyIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyLinkedList<?> that = (MyLinkedList<?>) o;

        Iterator iteratorThis;
        Iterator iteratorThat;


        if(this.size == that.size){
            iteratorThis = this.iterator();
            iteratorThat = that.iterator();
            System.out.println(iteratorThat.getClass());
            while (iteratorThis.hasNext() && iteratorThat.hasNext()){
                E x1 = (E) iteratorThis.next();
                E x2 = (E) iteratorThat.next();

                if(!Objects.equals(x1,x2)){
                    return false;
                }
            }
        }
        return true;

    }

    @Override
    public int hashCode() {
        int hashCode = 17;
        for (E e : this)
            hashCode = 31*hashCode + (e==null ? 0 : e.hashCode());
        return hashCode;
    }
}

package com.babatman;

import java.util.ArrayList;

/**
 * Created by deniz on 04.11.2016.
 */
public class Tree<E extends Comparable<E>> implements SearchTree<E>
{

    protected static class Node<E>{
        // Data Fields
        /** The size of a node */
        private static final int CAP = 26;

        /*The information*/
        private E data;
        private ArrayList<Integer> indexUrl = new ArrayList<Integer>();
        /**
         *  The links to the children.
         */
        public Node<E>[] child = new Node[CAP];

        public Node(E data)
        {
            this.data = data;
            for (int i = 0; i < CAP; i++) {
                child[i] = null;
            }
        }

        @Override
        public String toString() {
            return data.toString();
        }

        public ArrayList<Integer> getIndexUrl() {
            return indexUrl;
        }

        public void setIndexUrl(int indexUrl) {
            this.indexUrl.add(indexUrl);
        }
    }

    // Data Fields
    /** The reference to the root. */
    private Node < E > root = null;
    protected boolean addReturn ;

    public boolean add(E obj) {
        int indexChild = 0;
        int indexParent = 0;
        root = add(root, obj, indexChild, indexParent);
        return true;
    }

    private Node<E> add(Node<E> localRoot, E data, int indexChild, int indexParent)
    {
        if (localRoot == null)
        {
            return new Node<E>(data);
        }
        if (indexChild<localRoot.CAP && localRoot.child[indexChild] ==null) {
            Node<E> node = new Node<E>(data);
            localRoot.child[indexChild] = node;
            return localRoot;
        }
        else if (indexChild<26)
            localRoot = add(localRoot, data, indexChild+1, indexParent);

        else if (indexChild == localRoot.CAP)
        {
            indexChild = 0;
            if (root.child[indexParent].child[localRoot.CAP-1] != null) {
                ++indexParent;
                root.child[indexParent] = add(root.child[indexParent], data, indexChild, indexParent);
            }
            else
                root.child[indexParent] = add(root.child[indexParent], data, indexChild, indexParent);

        }

        return localRoot;
    }

    public ArrayList<Integer> find(E target) {
        int indexChiled = 0;
        int indexParent = 0;
        return  find(root, target, indexChiled, indexParent);
    }
    private ArrayList<Integer> find(Node<E> localRoot, E target, int indexChild, int indexParent){


        //first character of target
        String word = (String) target;
        String car = new String();
        //  localRoot.data.compareTo((E)c);

        if (word.equals("") == false)
            car= word.substring(0,1);

        if (word.equals("") == true && localRoot.child[indexChild] != null)
        {
            //ikili ve tekli kelimeker icin iplement edilicek
        }

        if (localRoot.child[indexChild] == null)
        {
            return localRoot.getIndexUrl();
        }

        if (localRoot.child[indexChild].data.compareTo((E)car) == 0)
        {
            return find(localRoot.child[indexChild], (E) word.substring(1,word.length()),indexChild=0, indexParent);
        }
        else return find(localRoot, target,++indexChild, indexParent);

    }

    public void setIndexUrl(E word, int indexUrl)
    {
        int indexChiled = 0;
        int indexParent = 0;
        setIndexUrl(root,word, indexUrl, indexChiled, indexParent );
    }

    private boolean setIndexUrl(Node<E>localRoot, E target, int indexUrl, int indexChild, int indexParent){


        //first character of target
        String word = (String) target;
        String car = new String();
        //  localRoot.data.compareTo((E)c);

        if (word.equals("") == false)
            car= word.substring(0,1);

        if (word.equals("") == true && localRoot.child[indexChild] != null)
        {
            //ikili ve tekli kelimeker icin iplement edilicek
        }

        if (localRoot.child[indexChild] == null)
        {
            localRoot.setIndexUrl(indexUrl);
            return true;
        }

        if (localRoot.child[indexChild].data.compareTo((E)car) == 0)
        {
           return setIndexUrl(localRoot.child[indexChild],(E) word.substring(1,word.length()),indexUrl,indexChild=0, indexParent);
        }
        else return setIndexUrl(localRoot,target,indexUrl,++indexChild, indexParent);

    }
/*
    public void display()
    {
        int index = 0;
        System.out.println(root.data.toString());
        display(root, index);
    }
    private void display(Node<E> locatRoot, int index)
    {
        if (locatRoot == null)
        {
            System.out.println("null");
            return;
        }
        else if (index<26)
        {
            System.out.println(locatRoot.child[index]);
            display(locatRoot, index+1);
        }
    }*/

}
















/*public boolean add(E obj) {
        int index = 0;
        root = add(root, obj, index);
        return true;
    }

    private Node<E> add(Node<E> localRoot, E data, int index)
    {
        if (localRoot == null)
        {
            return new Node<E>(data);
        }
        if (index<localRoot.CAP && localRoot.child[index] ==null) {
            Node<E> node = new Node<E>(data);
            localRoot.child[index] = node;
            return localRoot;
        }
        else if (index<26)
            localRoot = add(localRoot, data, index+1);


        return localRoot;
    }*/
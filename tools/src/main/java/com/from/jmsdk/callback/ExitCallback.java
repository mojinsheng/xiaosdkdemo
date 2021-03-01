package com.from.jmsdk.callback;

//TODO DOC
//TODO OP2 此类可能无法被外部引用，而此类需要被外部引用
// JM SDK Login callback, export to CP to get callback
public interface ExitCallback {

    void onExit();

    void onCancel();
}

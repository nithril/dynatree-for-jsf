function onlyDigits(evt) {
    var keycode;
 
    if (evt)
        ;
    else if (window.event)
        evt = window.event;
    else if (event)
        evt = event;
    else
        return true;
 
    if (evt.charCode)
        keycode = evt.charCode;
    else if (evt.keyCode)
        keycode = evt.keyCode;
    else if (evt.which)
        keycode = evt.which;
    else
        keycode = 0;

    return ((keycode >= 48 && keycode <= 57) || (keycode >= 37 && keycode <= 40) || (keycode == 8) || (keycode == 46));
}
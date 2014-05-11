    <script type = "text/javascript">
    var count = 2;
    function validate() {
    var un = document.myform.username.value;
    var pw = document.myform.pword.value;
    var valid = false;
    var unArray = ["username1", "username2", "username3", "username4"]; // the last username will not be followed by a comma
    var pwArray = ["password1", "password2", "password3", "password4"]; // the password for each user name 
    for (var i=0; i <unArray.length; i++) {
    if ((un == unArray[i]) && (pw == pwArray[i])) {
    valid = true;
    break;
    }
    }
    if (valid) {
    alert ("Login successful !! ");
    window.location = "http://www.google.com";
    return false;
    }
    var t = " tries";
    if (count == 1) {t = " try"}
    if (count >= 1) {
    alert ("Invalid username and/or password. You have " + count + t + " left.");
    document.myform.username.value = "";
    document.myform.pword.value = "";
    setTimeout("document.myform.username.focus()", 25);
    setTimeout("document.myform.username.select()", 25);
    count --;
    }
    else {
    alert ("Alert incorrect USERNAME/PASSWORD! Attempts exceeded ");
    document.myform.username.value = "Attempts exceeded & disabled!";
    document.myform.pword.value = "";
    document.myform.username.disabled = true;
    document.myform.pword.disabled = true;
    return false;
    }
    }
    </script>
    <form name = "myform">
    <p>ENTER USER NAME <input type="text" name="username"> ENTER PASSWORD <input type="password" name="pword">
    <input type="button" value="Check In" name="Submit" onclick= "validate()">
    </p>
    </form>


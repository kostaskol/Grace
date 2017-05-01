fun hello(z : int) : int
    var y : int; var x : char[3]; var k : int[5];
    {
        x[2] <- 'x';
        k[3] <- 2;
        y <- 1 + 2;
        z <- 3;
        z <- z + y + k[3];
        return 3;
    }
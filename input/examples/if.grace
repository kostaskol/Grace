fun hello() : nothing
    var y : int; var x : char[3];
    {
        y <- 3;
        x[2] <- y;
        y <- x[2];
    }
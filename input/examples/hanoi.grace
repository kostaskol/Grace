fun solve () : nothing
      fun hanoi (rings : int; ref source, target, auxiliary : char[]) : nothing
        fun move() : int;
      {
         if rings >= 1 then {
            hanoi(rings-1, source, auxiliary, target);
            move(source, target);
            hanoi(rings-1, auxiliary, target, source);
            if x >= 2 then {
                puts("If");
            } else {
                puts("Else");
            }
         }
      }

      var NumberOfRings : int;
{
  writeString("Rings: ");
  NumberOfRings <- geti();
  hanoi(NumberOfRings, "left", "right", "middle");
}

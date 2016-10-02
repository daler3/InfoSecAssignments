#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>


int main (void) {

setuid (0);

system(argv[1]);

return 0; 
}

#### Guidline
The project architecture is inspired by clean mvp and it is focused on testability of code.
- I am using dagger 2 so I can mock my responses for UI testing. This make supporting and adding UI test much faster.
- I am using PageObject patter in my UI test to clean up and reuse.
- I am using RxJava to communicate between my layers
- I am using Realm for persistence
 


#### Things to improve 
1. I would have used Epoxy for both of my pages. Since it remove the boiler work for RecyclerView.
2. I would focus on caching the restaurants and reuse them in restaurant detail page.
3. Redo the UI
4. Add error handling
5. Add phone orientation change support
6. Better naming
7. Package structure base on features
8. Add comments in the code

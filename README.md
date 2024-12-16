# ConnectHub

Hey there! 👋 Welcome to **ConnectHub**, this project were meant to be a college's project for OOP course, but me and my time agrred on flexing some software development muscles, casue why not. This project is all about showcasing design patterns, sticking to SOLID principles, and having a bit of fun along the way.

## What's Inside?

### Diagrams:
- **Class Diagram**: Get a snapshot of the project's structure.
- **Sequence Diagram**: See how different parts of the system interact over time.
- **State Diagram**: Understand how the system transitions between states.
- **Use Case Diagram**: Explore the various ways users can engage with the app.

### Data Goodies:
- **JSON Files**: Includes `chats.json`, `comments.json`, `content.json`, and more to simulate real-world data handling.

### Build Stuff:
- **build.xml**: Use this if you want to build the project yourself.

## Design Patterns Used

I've sprinkled in some classic design patterns to make the code cleaner and more efficient:

- **Singleton Pattern**: Ensures there's only one instance of certain classes, like the database connector, throughout the app.
- **Observer Pattern**: Powers the notification system, so components can listen and react to events (like new messages or comments).
- **Factory Pattern**: Simplifies object creation for different user types or content pieces without needing to know the exact class behind them.
- **Decorator Pattern**: Adds functionality to objects dynamically, such as enhancing user profiles with additional features.
- **Strategy Pattern**: Allows switching between different algorithms or behaviors at runtime, like changing sorting methods for content.

## Sticking to SOLID Principles

I've made a conscious effort to adhere to the SOLID principles to keep things neat and scalable:

1. **Single Responsibility Principle**: Each class does one thing and does it well. For instance, the `ChatManager` only handles chat-related operations.
2. **Open/Closed Principle**: The code is open for extension but closed for modification. You can add new features without altering existing code, thanks to abstractions.
3. **Liskov Substitution Principle**: Subclasses can stand in for their parent classes without the app crashing or behaving oddly.
4. **Interface Segregation Principle**: No bloated interfaces here! Classes only implement methods they actually use.
5. **Dependency Inversion Principle**: High-level modules don't depend on low-level modules; both rely on abstractions. This makes the code more flexible and easier to refactor.

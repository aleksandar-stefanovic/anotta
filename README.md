# Anotta
Another Note-Taking App

![Screenshot](screen.png)

## Goal
The goal is, as always with note-taking apps, to exercise design. In this case, it is meant to adhere to "Material Theming" redesign of Android (Started with Android 9.0 Pie), and is inspired by "whitespace" theme of Google apps.

## Synopsis
All of the notes are stored in the local database, using the Room Android Architecture Component, and the Room implementation is modeled after Room With A View codelab. At runtime, they are kept within a ViewModel, to survive configuration changes. They are displayed in a RecyclerView, within CoordinatorLayout (to coordinate and animate view, such as the FAB).

## State
The project is at its' inception, and so it isn't very special. It is being actively worked on, though. Currently, this project is **not** functional, and you can't actually take notes with it.

## Building
Clone the repository (from Android Studio directly, or by git commands), and build it like a regular Android Studio project. There shouldn't be issues, except if you have to download the latest libraries, however, if there are issues, report them.

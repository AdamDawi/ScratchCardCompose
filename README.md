# ScratchCardCompose
A customizable scratch card component built with Jetpack Compose, which allows users to "scratch" an overlay image to reveal a base image underneath. This component can be tailored with options for scratch line style, threshold percentage, and more.

## ⭐️Features
### Main Screen 
- **Scratch Overlay Masking:** The overlay image uses masking with transparency and blend modes to simulate realistic scratching effects.
  - The ``BlendMode.Clear`` ensures the scratched pixels are completely erased.
  - ``CompositingStrategy.Offscreen`` ensures that the base image remains unaffected by masking.
  
- **Threshold Control:** Define the percentage of the overlay that needs to be scratched to reveal the base image.

- **State Management:** The component tracks whether the card has been fully scratched and triggers a callback when it's complete.

## Installation
To use this component in your Jetpack Compose project, simply copy the ``ScratchCard``  composable into your project and customize it with your own images and configurations.

## Usage Example
Here’s an example of how to use the ScratchCard component:
```kotlin
  ScratchCard(
    modifier = Modifier
        .height(200.dp)
        .width(300.dp),
    overlayImage = ImageBitmap.imageResource(R.drawable.overlay_image),
    baseImage = painterResource(R.drawable.base_image),
    onScratchComplete = {
        // Handle when the card is completely scratched
    },
    isScratched = false,
    shape = RoundedCornerShape(12.dp),
    scratchLineWidth = 32.dp,
    scratchingThresholdPercentage = 0.7f
)
```

## How it works
The scratch card uses a layered rendering approach:
1. Base Image: Displayed at the bottom of the stack.
2. Overlay Image: Rendered above the base image and acts as a mask.
3. Scratch Lines: Drawn using Canvas and rendered with BlendMode.Clear to erase parts of the overlay image.

## Customize Options

| Parameter                     | Description                                                                                                   | Default               |
|-------------------------------|---------------------------------------------------------------------------------------------------------------|-----------------------|
| `modifier`                    | Modifier to be applied to the ScratchCard.                                                                   | `Modifier`           |
| `overlayImage`                | The image that will be scratched off by the user, typically a texture or effect to reveal the base image.     | _None_                |
| `baseImage`                   | The base image that will be revealed after the overlay is scratched off.                                     | _None_                |
| `scratchingThresholdPercentage` | The percentage of the area that needs to be scratched off to show the base image.                          | `0.8`                 |
| `scratchLineWidth`            | The width of the scratch lines drawn when the user drags their finger across the overlay.                    | `32.dp`               |
| `scratchLineCap`              | The shape of the stroke cap applied to the scratch lines.                                                    | `StrokeCap.Round`     |
| `isScratched`                 | Flag to determine if the scratch card is fully scratched. If true, no further scratching is possible.        | `false`               |
| `onScratchComplete`           | Callback triggered when the scratch card is fully scratched, meaning the threshold has been reached.         | `{}` (empty lambda)   |
| `shape`                       | The shape of the scratch card, typically a rounded rectangle or custom shape.                                | `RoundedCornerShape(12.dp)` |


## Here are some overview videos:
![1](https://github.com/user-attachments/assets/95a3ed0a-d98b-49af-9149-c68091bdd041)
![2](https://github.com/user-attachments/assets/e85662d1-dc63-498a-963b-1d4a5bb674c9)

## Requirements
Minimum version: Android 7.0 (API level 24) or later📱

Target version: Android 14 (API level 34) or later📱

## Author
Adam Dawidziuk🧑‍💻

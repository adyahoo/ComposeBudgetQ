---
name: BudgetQ
colors:
  surface: "#faf8ff"
  surface-dim: "#d2d9f4"
  surface-bright: "#faf8ff"
  surface-container-lowest: "#ffffff"
  surface-container-low: "#f2f3ff"
  surface-container: "#eaedff"
  surface-container-high: "#e2e7ff"
  surface-container-highest: "#dae2fd"
  on-surface: "#131b2e"
  on-surface-variant: "#464555"
  inverse-surface: "#283044"
  inverse-on-surface: "#eef0ff"
  outline: "#777587"
  outline-variant: "#c7c4d8"
  surface-tint: "#4d44e3"
  primary: "#3525cd"
  on-primary: "#ffffff"
  primary-container: "#4f46e5"
  on-primary-container: "#dad7ff"
  inverse-primary: "#c3c0ff"
  secondary: "#006c49"
  on-secondary: "#ffffff"
  secondary-container: "#6cf8bb"
  on-secondary-container: "#00714d"
  tertiary: "#960014"
  on-tertiary: "#ffffff"
  tertiary-container: "#bc1d25"
  on-tertiary-container: "#ffd0cc"
  error: "#ba1a1a"
  on-error: "#ffffff"
  error-container: "#ffdad6"
  on-error-container: "#93000a"
  primary-fixed: "#e2dfff"
  primary-fixed-dim: "#c3c0ff"
  on-primary-fixed: "#0f0069"
  on-primary-fixed-variant: "#3323cc"
  secondary-fixed: "#6ffbbe"
  secondary-fixed-dim: "#4edea3"
  on-secondary-fixed: "#002113"
  on-secondary-fixed-variant: "#005236"
  tertiary-fixed: "#ffdad7"
  tertiary-fixed-dim: "#ffb3ad"
  on-tertiary-fixed: "#410004"
  on-tertiary-fixed-variant: "#930013"
  background: "#faf8ff"
  on-background: "#131b2e"
  surface-variant: "#dae2fd"
typography:
  display-lg:
    fontFamily: Plus Jakarta Sans
    fontSize: 40px
    fontWeight: "700"
    lineHeight: 48px
    letterSpacing: -0.02em
  display-lg-mobile:
    fontFamily: Plus Jakarta Sans
    fontSize: 32px
    fontWeight: "700"
    lineHeight: 40px
    letterSpacing: -0.02em
  headline-lg:
    fontFamily: Plus Jakarta Sans
    fontSize: 28px
    fontWeight: "700"
    lineHeight: 36px
    letterSpacing: -0.015em
  headline-md:
    fontFamily: Plus Jakarta Sans
    fontSize: 22px
    fontWeight: "600"
    lineHeight: 28px
    letterSpacing: -0.01em
  headline-sm:
    fontFamily: Plus Jakarta Sans
    fontSize: 18px
    fontWeight: "600"
    lineHeight: 24px
    letterSpacing: -0.005em
  title-md:
    fontFamily: Plus Jakarta Sans
    fontSize: 16px
    fontWeight: "600"
    lineHeight: 22px
    letterSpacing: 0em
  body-lg:
    fontFamily: Plus Jakarta Sans
    fontSize: 16px
    fontWeight: "400"
    lineHeight: 24px
    letterSpacing: 0em
  body-md:
    fontFamily: Plus Jakarta Sans
    fontSize: 14px
    fontWeight: "400"
    lineHeight: 20px
    letterSpacing: 0em
  body-sm:
    fontFamily: Plus Jakarta Sans
    fontSize: 12px
    fontWeight: "400"
    lineHeight: 16px
    letterSpacing: 0.01em
  numeric-metric:
    fontFamily: Plus Jakarta Sans
    fontSize: 32px
    fontWeight: "700"
    lineHeight: 36px
    letterSpacing: -0.03em
  numeric-data:
    fontFamily: Plus Jakarta Sans
    fontSize: 16px
    fontWeight: "600"
    lineHeight: 20px
    letterSpacing: -0.01em
  label-md:
    fontFamily: Plus Jakarta Sans
    fontSize: 12px
    fontWeight: "600"
    lineHeight: 16px
    letterSpacing: 0.04em
  label-sm:
    fontFamily: Plus Jakarta Sans
    fontSize: 10px
    fontWeight: "700"
    lineHeight: 14px
    letterSpacing: 0.06em
rounded:
  sm: 0.25rem
  DEFAULT: 0.5rem
  md: 0.75rem
  lg: 1rem
  xl: 1.5rem
  full: 9999px
spacing:
  gutter: 1rem
  gutter-mobile: 0.75rem
  margin: 1rem
  margin-desktop: 2rem
  space-xs: 0.25rem
  space-sm: 0.5rem
  space-md: 1rem
  space-lg: 1.5rem
  space-xl: 2rem
---

# Project Design Document: BudgetQ

## 1. Executive Summary

- **Purpose**: Increase user's financial awareness by limiting budget of each user's needs.
- **Target Audience**: Personal use
- **Development Plan**: The app should be developed with KMP multi-platform and support Android & IOS at the final stage. For now, develop the app with Android-First paradigm.

## 2. Tech Stack & Architecture

- **Language**: Kotlin
- **Design Pattern**: MVVM
- **Project Structure**: Simple Clean Architecture (Data Layer, Domain Layer, Presentation Layer)
- **UI Framework**: Jetpack Compose
- **Dependency Injection**: Hilt
- **Asynchrony**: Kotlin Coroutines & Flow
- **Networking**: Ktor
- **Local Database**: Room (If Needed)

## 3. Brand & Style

The design system is engineered for uncompromising clarity, control, and real-time behavioral nudging. Targeted at financially proactive professionals, digital nomads, and budget-conscious tech natives, it replaces traditional financial anxiety with calm, analytical command.

The aesthetic fuses **Corporate/Modern Precision** with **High-Contrast Data Architecture**. It prioritizes immediate scannability over decorative flourish: crisp line-work, high foreground-to-background contrast ratios, structured micro-surfaces, and clear status signaling. Surfaces feel tactile and solid rather than ethereal, ensuring financial data—limits, velocity, and thresholds—is processed at a glance without cognitive drag.

## 4. Colors

The palette establishes an unambiguous hierarchy anchored by functional status and legibility.

- **Primary (`#4F46E5` - Electric Indigo):** Reserved for intentional primary actions, active navigational states, and focus triggers.
- **Secondary (`#10B981` - Emerald Green):** Signals stability, positive surplus, within-budget statuses, and safe burn rates.
- **Tertiary (`#EF4444` - Vibrant Coral Red):** Alerts the user to exceeded budget limits, account friction, and negative delta variances.
- **Warning (`#F59E0B` - Amber Flare):** Dedicated functional color for caution metrics, approaching limits (80–99% depletion), and cycle transition notices.
- **Neutral Surface Palette:**
  - Base Background: `#F8FAFC` (Canvas light) / `#0B0F19` (Alternative dark mode ground).
  - Surface Card: `#FFFFFF` (Light) / `#111827` (Dark elevated).
  - Crisp Borders: `#E2E8F0` (Light micro-borders) / `#1E293B` (Dark dividers).
  - Deep Text Neutral: `#0F172A` (Primary 900) down to `#64748B` (Muted 500 metadata).

Semantic colors (emerald, amber, coral) must strictly denote financial states and never be used purely for decoration.

## 5. Typography

The type system is anchored in **Plus Jakarta Sans**, chosen for its geometric precision, crisp vertical metrics, and contemporary stance.

- **Tabular Figures (`tnum`):** All financial values, decimal currencies, balances, and time stamps must activate OpenType tabular numbers (`font-variant-numeric: tabular-nums;`). This guarantees alignment across transaction feeds and financial ledgers.
- **Hierarchy Separation:** Headers and key balance summaries leverage tight tracking (`-0.02em` to `-0.01em`) and bold weights to ground the eye. Badges, frequency tags, and meta labels run in uppercase with expanded tracking (`0.04em` to `0.06em`) for rapid identification.

## 6. Layout & Spacing

The layout is built upon an 8pt spatial grid (with a 4pt sub-grid for badges and tight micro-alignments) centered on fluid mobile-first ergonomics.

- **Mobile Viewport Structure:** Standard outer margin is fixed at `1rem` (16px), keeping content within the thumb zone. Stacked card groups leverage `space-md` (16px) gaps.
- **Micro-Gaps:** Icon-to-text separation uses `space-xs` (4px) to `space-sm` (8px). Structural padding inside budget cards runs strictly at `space-md` or `space-lg`.
- **Responsive Adaptability:** On tablet and wide surfaces, max content constraints cap at 480px for dedicated single-column handheld feeds, or pivot into a two-column 24px gutter layout where spending charts pair alongside transaction lists.

## 7. Elevation & Depth

Visual architecture relies on **crisp low-contrast outlines** and **subtle ambient elevation**, avoiding deep skeuomorphic shadows or muddy drop-shadows.

- **Level 0 (Canvas):** Flat base surface (`#F8FAFC`). No borders, zero elevation.
- **Level 1 (Card & Module):** `#FFFFFF` surface resting on a crisp 1px structural border (`#E2E8F0`). Underpinned by a soft ambient shadow: `0px 1px 3px rgba(15, 23, 42, 0.04), 0px 4px 8px -2px rgba(15, 23, 42, 0.02)`.
- **Level 2 (Active/Floating & Sheets):** Bottom navigation docks, modal action sheets, and dragged cards. Uses a 1px border paired with `0px 10px 24px -4px rgba(15, 23, 42, 0.08), 0px 4px 6px -2px rgba(15, 23, 42, 0.03)`.
- **Level 3 (Popovers & Critical Alerts):** `0px 20px 32px -8px rgba(15, 23, 42, 0.12)`.

## 8. Shapes

The design system operates with a structured, modern curvature (`roundedness: 2`).

- Standard containers and cards adopt `rounded-xl` to `rounded-2xl` (16px to 24px corner radius) to soften dense analytical content and improve visual pacing.
- Interactive elements (buttons, segmented cycle selectors, input text fields) standardize on 10px to 12px radii.
- Badges, pills, progress tracks, and cycle filters use full-radius pill silhouettes (`rounded-full` / 9999px) to differentiate status indicators from content cards.

## 9. Components

### Buttons

- **Primary:** Background `#4F46E5`, label `#FFFFFF`, 12px radius, 48px height for thumb accessibility. On press: `#4338CA`.
- **Secondary / Subtle:** Surface `#F1F5F9`, label `#0F172A`, 1px border `#E2E8F0`.
- **Destructive:** Background `#FEE2E2`, label `#EF4444`, 1px border `#FCA5A5`.

### Expense Cards (`rounded-2xl`)

- White surface, 1px `#E2E8F0` border, `16px` inner padding.
- Top row: Category avatar/icon (40x40px, rounded-xl), category name, cycle tag (`Daily`, `Weekly`, `Monthly`), and amount remaining in bold tabular figures.
- Bottom row: Linear progress bar indicating cycle capacity, backed by context micro-copy ("$42 left of $300 limit").

### Progress Bars

- 8px height, continuous `rounded-full` geometry.
- Base track: `#F1F5F9`.
- Fill semantics:
  - 0% to 75%: `#10B981` (Safe)
  - 76% to 95%: `#F59E0B` (Caution)
  - 96% to 100%+: `#EF4444` (Exceeded / Warning)

### Status Badges & Cycle Tags

- Height: 24px, pill-shaped (`rounded-full`), `space-xs` (4px) vertical and `space-sm` (8px) horizontal padding.
- `Daily`: Indigo tint (`bg-[#EEF2FF] text-[#4F46E5]`).
- `Weekly`: Slate tint (`bg-[#F1F5F9] text-[#475569]`).
- `Monthly`: Purple tint (`bg-[#FAF5FF] text-[#7E22CE]`).
- Status alerts: Solid micro-dot (6px) followed by status label (`Within Budget`, `Near Limit`, `Over Budget`).

### Inputs & Segmented Controls

- **Inputs:** 48px minimum height, 1px border `#E2E8F0`, `#FFFFFF` fill. Focus: 2px ring `#4F46E5`.
- **Cycle Switcher:** Embedded 40px segment pill with `#F1F5F9` tray background. The selected cycle toggle takes a crisp `#FFFFFF` elevated pill with an ambient micro-shadow.

### Transaction Row Lists

- Zero outer card borders when stacked inside feeds; partitioned by thin 1px `#F1F5F9` dividers with left inset alignment matching title typography.

## 10. Key User Flows & Features

### Main Screen

- Has bottom navigation with 3 options.

### Feature 1: Dashboard & Budget Management

- Show monthly expense & budget statistics.
- Show current list budget with filter all, daily, weekly, monthly.
- Has FAB for navigating to Add Budget Screen

### Feature 2: Record Expense

- Record new expense by inputting "expense amount" and "budget category".
- Simple calculation about current budget if new expense submitted.
- Have state where new expense exceed current budget or not.

### Feature 3: Submission History

- Show all history of expense submission records.
- Has filter with values: All, Exceeded Limit, Within Limit.
- Can search history by name.

## 11. AI Prompt Instructions

When generating code for this project, always:

1. Follow the MVVM pattern with state tracking via `MutableStateFlow`.
2. Keep UI components as reusable `@Composable` functions.
3. Place business logic in the `ViewModel`, never in the UI layer.

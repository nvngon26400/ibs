<script>
import { h } from 'vue'
const ItemRendering = (props, context) => {
  const { icon, title, image, externalIcon } = props
  const vnodes = []

  if (icon) {
    vnodes.push(h('i', { class: [`${icon}`, 'svg-icon'] }))
  }

  if (image) {
    vnodes.push(h('img',
      {
        slot: 'title',
        src: image.src,
        alt: image.alt,
        style: `height: ${image.height}`
      }
    ))
  }

  if (title) {
    vnodes.push(h('span', { slot: 'title', class: 'sub-el-title' }, title))
  }

  if (externalIcon) {
    vnodes.push(
      h('i', {
        class: 'seeds-external',
        style: 'margin-left: 20px;'
      })
    )
  }

  return vnodes
}
ItemRendering.props = {
  icon: {
    type: String,
    default: ''
  },
  title: {
    type: String,
    default: ''
  },
  image: {
    type: Object,
    default: null
  },
  externalIcon: {
    type: Boolean,
    default: false
  }
}
export default ItemRendering
</script>

<style scoped lang="scss">
.sub-el-icon {
  color: currentColor;
  width: 1em;
  height: 1em;
}

.sub-el-title {
  display:inline-block;
  line-height:20px;
  white-space: pre
}
</style>

<template>
	<div>
		<template v-for="val in chil" >
			<el-submenu :index="val.path" :key="val.path" v-if="val.children && val.children.length > 0" >
				<template slot="title" style=" background-color: #9a6e3a">
					<i :class="val.meta.icon"></i>
					<span>{{ $t(val.meta.title) }}</span>
				</template>
				<sub-item :chil="val.children" />
			</el-submenu>
			<template v-else>
				<el-menu-item :index="val.path" :key="val.path" >
					<template v-if="!val.meta.isLink || (val.meta.isLink && val.meta.isIframe)"  >
						<span style="color: #ffffff"><i :class="val.meta.icon ? val.meta.icon : ''"></i></span>
						<span style="color: #ffffff">{{ $t(val.meta.title) }}</span>
					</template>
					<template v-else>
						<a :href="val.meta.isLink" target="_blank">
							<i :class="val.meta.icon ? val.meta.icon : ''"></i>
							{{ $t(val.meta.title) }}
						</a>
					</template>
				</el-menu-item>
			</template>
		</template>
	</div>
</template>

<script>
export default {
	name: 'subItem',
	props: {
		chil: {
			type: Array,
			default() {
				return [];
			},
		},
	},
};
</script>
